package org.example.orchestra;

import java.util.Map;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Summary {
  @NonNull Map<String, Integer> totalCountByInstrument;

  @NonNull Map<String, Double> totalQuantityByInstrument;
}
