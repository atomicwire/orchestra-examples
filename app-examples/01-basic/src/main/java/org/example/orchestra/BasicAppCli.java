package org.example.orchestra;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@Slf4j
@CommandLine.Command(
    name = "basic-app",
    mixinStandardHelpOptions = true,
    description = "Demonstrate basic usage of the Orchestra Java library")
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class BasicAppCli implements Callable<Integer> {

  private static final JsonMapper JSON_MAPPER =
      JsonMapper.builder().configure(SerializationFeature.INDENT_OUTPUT, true).build();

  @CommandLine.Option(
      names = {"--input-dir"},
      required = true,
      description = "Where to read input data from (FIX 4.4 tag/value-encoded messages)")
  private Path inputDir;

  @CommandLine.Option(
      names = {"--output-file"},
      required = true,
      description = "Where to write the summary output")
  private Path outputFile;

  @CommandLine.Option(
      names = {"--tag-value-separator"},
      description = "Tag/value encoding field separator")
  private Optional<Character> tagValueSeparator;

  public static void main(String[] args) {
    var exitCode = new CommandLine(new BasicAppCli()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    log.info("Input dir: {}", inputDir);
    log.info("Output file: {}", outputFile);
    tagValueSeparator.ifPresent(value -> log.info("Tag/value separator: {}", value));

    final var config =
        BasicAppConfig.builder()
            .inputDir(inputDir)
            .tagValueSeparator(tagValueSeparator.orElse(null))
            .build();

    final Summary summary;
    try {
      summary = BasicApp.of(config).processInput();
    } catch (Exception e) {
      log.error("Failed to process data", e);
      return 1;
    }

    log.info("Summary: {}", summary);

    try (final var outputStream = Files.newOutputStream(outputFile)) {
      JSON_MAPPER.writeValue(outputStream, summary);
    } catch (IOException e) {
      log.error("Failed to render summary as JSON", e);
      return 1;
    }

    return 0;
  }
}
