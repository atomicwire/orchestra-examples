package org.example.orchestra;

import java.nio.file.Path;
import java.util.Optional;
import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicAppConfig {
  /** Where to read input data from (FIX 4.4 tag/value-encoded messages). */
  @NonNull Path inputDir;

  /** Tag/value encoding field separator. */
  Character tagValueSeparator;

  public Optional<Character> getTagValueSeparator() {
    return Optional.ofNullable(tagValueSeparator);
  }
}
