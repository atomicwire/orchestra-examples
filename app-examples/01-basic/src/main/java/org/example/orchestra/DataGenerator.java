package org.example.orchestra;

import io.atomicwire.orchestra.id.OrchestraFieldId;
import io.atomicwire.orchestra.spec.fixlatest.FixLocalMarketDateConverter;
import io.atomicwire.orchestra.spec.fixlatest.FixUtcTimestampConverter;
import io.atomicwire.orchestra.type.InstantPlatformType;
import io.atomicwire.orchestra.type.LocalDatePlatformType;
import jakarta.annotation.Nonnull;
import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.example.orchestra.fix44.Fields;

/** A data generator for FIX 4.4 NewOrderSingle messages for use in the {@link BasicApp}. */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DataGenerator {

  private static final Character TAG_VALUE_FIELD_SEPARATOR = '\1';

  private static final List<String> SENDER_COMP_IDS =
      List.of("CLIENT1", "CLIENT2", "CLIENT3", "CLIENT4", "CLIENT5");

  private static final List<String> TARGET_COMP_IDS =
      List.of("BROKER1", "BROKER2", "BROKER3", "BROKER4", "BROKER5");

  private static final List<InstrumentInfo> INSTRUMENTS =
      List.of(
          new InstrumentInfo("SYM01", "S001", 1597 / 200.),
          new InstrumentInfo("SYM02", "S002", 2584 / 200.),
          new InstrumentInfo("SYM03", "S003", 4181 / 200.),
          new InstrumentInfo("SYM04", "S004", 6765 / 200.),
          new InstrumentInfo("SYM05", "S005", 10946 / 200.),
          new InstrumentInfo("SYM06", "S006", 17711 / 200.),
          new InstrumentInfo("SYM07", "S007", 28657 / 200.),
          new InstrumentInfo("SYM08", "S008", 46368 / 200.),
          new InstrumentInfo("SYM09", "S009", 75025 / 200.),
          new InstrumentInfo("SYM10", "S010", 121393 / 200.));

  private static final FixUtcTimestampConverter FIX_UTC_TIMESTAMP_CONVERTER =
      new FixUtcTimestampConverter();
  private static final FixLocalMarketDateConverter FIX_LOCAL_MARKET_DATE_CONVERTER =
      new FixLocalMarketDateConverter();

  @NonNull private final DataGeneratorConfig config;
  @With @NonNull private final Clock clock;
  @With @NonNull private final Random random;

  public static DataGenerator of(DataGeneratorConfig config) {
    return new DataGenerator(config, Clock.systemUTC(), ThreadLocalRandom.current());
  }

  public List<String> generateMessages() {
    return IntStream.range(0, config.getMessageCount()).mapToObj(this::generateMessage).toList();
  }

  private String generateMessage(int seqNum) {
    final var transactTimeZonedDateTime = ZonedDateTime.now(clock);
    final var sendingTimeInstant = generateSendingTimeInstant(transactTimeZonedDateTime);
    final var settlDateLocalDate = generateSettlDateLocalDate(transactTimeZonedDateTime);

    final var sendingTime =
        FIX_UTC_TIMESTAMP_CONVERTER.convertFromPlatform(InstantPlatformType.of(sendingTimeInstant));
    final var settlDate =
        FIX_LOCAL_MARKET_DATE_CONVERTER.convertFromPlatform(
            LocalDatePlatformType.of(settlDateLocalDate));
    final var minQty = generateMinQty();
    final var instrumentInfo = randomChoice(INSTRUMENTS);
    final var transactTime =
        FIX_UTC_TIMESTAMP_CONVERTER.convertFromPlatform(
            InstantPlatformType.of(transactTimeZonedDateTime.toInstant()));

    final var fieldsAndValues =
        List.of(
            new FieldAndValue(Fields.BeginString, "FIX.4.4"),
            new FieldAndValue(Fields.MsgType, "D"),
            new FieldAndValue(Fields.SenderCompID, randomChoice(SENDER_COMP_IDS)),
            new FieldAndValue(Fields.TargetCompID, randomChoice(TARGET_COMP_IDS)),
            new FieldAndValue(Fields.MsgSeqNum, Integer.toString(seqNum)),
            new FieldAndValue(Fields.SendingTime, sendingTime),
            new FieldAndValue(Fields.ClOrdID, generateClOrdId()),
            new FieldAndValue(Fields.Account, generateAccount()),
            new FieldAndValue(Fields.SettlType, "0"),
            new FieldAndValue(Fields.SettlDate, settlDate),
            new FieldAndValue(Fields.HandlInst, "1"),
            new FieldAndValue(Fields.MinQty, Integer.toString(minQty)),
            new FieldAndValue(Fields.MaxFloor, Integer.toString(minQty * 50)),
            new FieldAndValue(Fields.Symbol, instrumentInfo.symbol),
            new FieldAndValue(Fields.SecurityID, instrumentInfo.securityId),
            new FieldAndValue(Fields.SecurityIDSource, "1"),
            new FieldAndValue(Fields.Side, generateSide()),
            new FieldAndValue(Fields.TransactTime, transactTime),
            new FieldAndValue(Fields.OrderQty, Integer.toString(minQty * 10)),
            new FieldAndValue(Fields.OrdType, "1"),
            new FieldAndValue(Fields.Price, Double.toString(generatePrice(instrumentInfo))),
            new FieldAndValue(Fields.Currency, "XTS"),
            new FieldAndValue(Fields.TimeInForce, "0"));

    return renderFieldsAndValues(fieldsAndValues);
  }

  private <T> T randomChoice(@Nonnull List<T> list) {
    return list.get(random.nextInt(list.size()));
  }

  private Instant generateSendingTimeInstant(@Nonnull ZonedDateTime transactTimeZonedDateTime) {
    final var offsetNanos = random.nextLong(10_000, 50_000_000);
    return transactTimeZonedDateTime.toInstant().minusNanos(offsetNanos);
  }

  private LocalDate generateSettlDateLocalDate(@Nonnull ZonedDateTime transactTimeZonedDateTime) {
    return transactTimeZonedDateTime.toLocalDate().plusDays(1);
  }

  private String generateClOrdId() {
    return "O%d".formatted(random.nextInt(100_000, 1_000_000));
  }

  private String generateAccount() {
    return "A%d".formatted(random.nextInt(100_000, 1_000_000));
  }

  private int generateMinQty() {
    return random.nextInt(100, 1000);
  }

  private String generateSide() {
    return random.nextBoolean() ? "1" : "2";
  }

  private double generatePrice(InstrumentInfo instrumentInfo) {
    return random.nextGaussian(instrumentInfo.marketPrice, instrumentInfo.marketPrice / 200.);
  }

  private String renderFieldsAndValues(@Nonnull List<FieldAndValue> fieldsAndValues) {
    final var sb = new StringBuilder();

    fieldsAndValues.forEach(
        fieldAndValue -> {
          sb.append(fieldAndValue);
          sb.append(TAG_VALUE_FIELD_SEPARATOR);
        });

    return sb.toString();
  }

  private record FieldAndValue(@NonNull OrchestraFieldId fieldId, @NonNull String stringValue) {
    @Override
    public String toString() {
      return "%d=%s".formatted(fieldId.getId(), stringValue);
    }
  }

  private record InstrumentInfo(
      @NonNull String symbol, @NonNull String securityId, @NonNull Double marketPrice) {}
}
