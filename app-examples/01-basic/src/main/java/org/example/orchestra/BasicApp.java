/*
 * Copyright 2024 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.atomicwire.orchestra.accessor.quickfix.QuickFixResourceAccessorConfig;
import io.atomicwire.orchestra.accessor.quickfix.QuickFixResourceAccessorFactory;
import io.atomicwire.orchestra.spec.OrchestraSpecBox;
import io.atomicwire.orchestra.spec.OrchestraSpecLoadException;
import io.atomicwire.orchestra.spec.OrchestraSpecLoader;
import io.atomicwire.orchestra.spec.OrchestraSpecSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
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
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicApp {

  private static final OrchestraSpecSource SPEC_SOURCE = OrchestraSpecSource.of("aw:fix-44");

  private static final JsonMapper JSON_MAPPER =
      JsonMapper.builder().configure(SerializationFeature.INDENT_OUTPUT, true).build();

  /** Where to read input data from (FIX 4.4 tag/value-encoded messages). */
  @NonNull private final Path inputDir;

  public static BasicApp ofInputDir(Path inputDir) {
    return new BasicApp(inputDir);
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      log.error("usage: {} <input-dir> <output-file>", BasicApp.class.getSimpleName());
      System.exit(1);
    }

    final var inputDir = Path.of(args[0]);
    final var outputFile = Path.of(args[1]);

    final Summary summary;
    try {
      summary = BasicApp.ofInputDir(inputDir).processInput();
    } catch (Exception e) {
      log.error("Failed to process data", e);
      System.exit(1);
      throw new RuntimeException("unreachable");
    }

    log.info("Summary: {}", summary);

    try (final var outputStream = Files.newOutputStream(outputFile)) {
      JSON_MAPPER.writeValue(outputStream, summary);
    } catch (IOException e) {
      log.error("Failed to render summary as JSON", e);
      System.exit(1);
    }
  }

  public Summary processInput() {
    final var quickfixAccessorFactory =
        QuickFixResourceAccessorFactory.of(
            loadSpec(), QuickFixResourceAccessorConfig.DEFAULT_CONFIG);

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

  private OrchestraSpecBox loadSpec() {
    try {
      return OrchestraSpecBox.from(OrchestraSpecLoader.loadOne(SPEC_SOURCE));
    } catch (OrchestraSpecLoadException e) {
      throw new RuntimeException("Failed to load Orchestra spec", e);
    }
  }

  private void applyToEachInputLine(Consumer<String> fn) {
    try (final var inputFiles = Files.list(inputDir)) {
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

  @Value
  @Builder
  @Jacksonized
  public static class Summary {
    @NonNull Map<String, Integer> totalCountByInstrument;

    @NonNull Map<String, Double> totalQuantityByInstrument;
  }
}
