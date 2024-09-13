package org.example.orchestra;

import io.atomicwire.orchestra.accessor.quickfix.QuickFixResourceAccessorConfig;
import io.atomicwire.orchestra.accessor.quickfix.QuickFixResourceAccessorFactory;
import io.atomicwire.orchestra.spec.OrchestraSpecBox;
import io.atomicwire.orchestra.spec.OrchestraSpecLoadException;
import io.atomicwire.orchestra.spec.OrchestraSpecLoader;
import io.atomicwire.orchestra.spec.OrchestraSpecSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.TreeMap;
import java.util.function.Consumer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orchestra.fix44.Messages;
import org.example.orchestra.fix44.messages.NewOrderSingle;

/**
 * A basic app that demonstrates parsing and using data according to an Orchestra spec (FIX 4.4).
 *
 * <ul>
 *   <li>Read input from files in the configured directory line by line
 *   <li>Parse the tag/value-encoded data using QuickFIX (via a {@link
 *       QuickFixResourceAccessorFactory}
 *   <li>Sum the total order count and quantity of all {@link NewOrderSingle} messages
 *   <li>Return a {@link Summary} of the statistics
 * </ul>
 */
@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class BasicApp {

  private static final OrchestraSpecSource SPEC_SOURCE = OrchestraSpecSource.of("aw:fix-44");

  @NonNull private final BasicAppConfig config;

  public Summary processInput() {
    final var quickfixAccessorFactory = createQuickFixAccessorFactory();

    final var totalCountByInstrument = new TreeMap<String, Integer>();
    final var totalQuantityByInstrument = new TreeMap<String, Double>();

    applyToEachInputLine(
        line -> {
          final var messageAccessor = quickfixAccessorFactory.getAccessor(line);
          final var messageType = messageAccessor.getMessageType();

          if (messageType.equals(Messages.NewOrderSingle)) {
            final var newOrderSingle = new NewOrderSingle(messageAccessor);

            final var clOrdId = newOrderSingle.getClOrdID();
            final var symbol = newOrderSingle.getInstrument().getSymbol();
            log.info(
                "Saw a {} message with ClOrdID {} and Symbol {}",
                messageType.getName(),
                clOrdId,
                symbol);

            totalCountByInstrument.put(symbol, totalCountByInstrument.getOrDefault(symbol, 0) + 1);
            totalQuantityByInstrument.put(
                symbol,
                totalQuantityByInstrument.getOrDefault(symbol, 0.)
                    + newOrderSingle.getOrderQtyData().getOrderQty().doubleValue());
          } else {
            log.info("Ignoring message with type {}", messageType.getName());
          }
        });

    return Summary.builder()
        .totalCountByInstrument(totalCountByInstrument)
        .totalQuantityByInstrument(totalQuantityByInstrument)
        .build();
  }

  private QuickFixResourceAccessorFactory createQuickFixAccessorFactory() {
    final var quickfixAccessorConfigBuilder = QuickFixResourceAccessorConfig.builder();
    config.getTagValueSeparator().ifPresent(quickfixAccessorConfigBuilder::delimiter);

    return QuickFixResourceAccessorFactory.of(loadSpec(), quickfixAccessorConfigBuilder.build());
  }

  private OrchestraSpecBox loadSpec() {
    try {
      return OrchestraSpecBox.from(OrchestraSpecLoader.loadOne(SPEC_SOURCE));
    } catch (OrchestraSpecLoadException e) {
      throw new RuntimeException("Failed to load Orchestra spec", e);
    }
  }

  private void applyToEachInputLine(Consumer<String> fn) {
    try (final var inputFiles = Files.list(config.getInputDir())) {
      inputFiles
          .filter(Files::isRegularFile)
          .forEach(
              inputFile -> {
                try (final var lines = Files.lines(inputFile, StandardCharsets.UTF_8)) {
                  lines.forEach(fn);
                } catch (IOException e) {
                  throw new RuntimeException("Failed to read input file", e);
                }
              });
    } catch (IOException e) {
      throw new RuntimeException("Failed to list input files", e);
    }
  }
}
