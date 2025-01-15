/*
 * Copyright 2025 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra.springboot;

import org.example.orchestra.springboot.api.InstrumentApiDelegate;
import org.example.orchestra.springboot.model.InstrumentComponent;
import org.example.orchestra.springboot.model.ProductCodeSet;
import org.example.orchestra.springboot.model.SecurityIDSourceCodeSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * An example of how to implement an API route.
 *
 * <p>The {@link InstrumentApiDelegate} interface is generated by the OpenAPI Generator, and we
 * provide the actual behavior here.
 */
@Component
public class InstrumentApiDelegateImpl implements InstrumentApiDelegate {
  @Override
  public ResponseEntity<InstrumentComponent> getInstrumentBySymbol(String symbol) {
    var instrument = getDummyInstrument();

    return ResponseEntity.ok(instrument);
  }

  private InstrumentComponent getDummyInstrument() {
    return new InstrumentComponent()
        .symbol("IBM")
        .securityID("459200-10-1")
        .securityIDSource(SecurityIDSourceCodeSet.CUSIP)
        .product(ProductCodeSet.EQUITY)
        .cfICode("ESNUOB");
  }
}
