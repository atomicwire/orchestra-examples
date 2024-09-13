/*
 * Copyright 2024 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra;

import static java.util.Objects.requireNonNull;

import lombok.*;

@Value
@Builder
public class DataGeneratorConfig {
  Integer messageCount;

  private DataGeneratorConfig(Integer messageCount) {
    if (requireNonNull(messageCount, "messageCount") < 0) {
      throw new IllegalArgumentException("messageCount must be >= 0");
    }

    this.messageCount = messageCount;
  }
}
