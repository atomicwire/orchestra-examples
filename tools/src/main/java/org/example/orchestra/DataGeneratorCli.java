/*
 * Copyright 2024 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@Slf4j
@CommandLine.Command(
    name = "data-generator",
    mixinStandardHelpOptions = true,
    description = "Generate data for the basic app")
public class DataGeneratorCli implements Callable<Integer> {

  @CommandLine.Option(
      names = {"--message-count"},
      defaultValue = "10",
      description = "The number of messages to generate (Default: ${DEFAULT-VALUE})")
  private Integer messageCount;

  @CommandLine.Option(
      names = {"--output-file"},
      required = true,
      description = "Where to write the generated data")
  private Path outputFile;

  public static void main(String[] args) {
    var exitCode = new CommandLine(new DataGeneratorCli()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    log.info("Message count: {}", messageCount);
    log.info("Output file: {}", outputFile);

    final var config = DataGeneratorConfig.builder().messageCount(messageCount).build();

    final var messages = DataGenerator.of(config).generateMessages();

    try {
      Files.write(outputFile, messages);
    } catch (IOException e) {
      log.error("Failed to render summary as JSON", e);
      return 1;
    }

    return 0;
  }
}
