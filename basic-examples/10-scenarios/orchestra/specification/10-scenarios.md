# Instrument Scenarios Example version 1.0

| Term       | Value                                                                                                                                                                                                                                                                                                                               |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| title      | Instrument Scenarios Example                                                                                                                                                                                                                                                                                                        |
| creator    | Atomic Wire Technology Limited                                                                                                                                                                                                                                                                                                      |
| created    | 2024-11-01                                                                                                                                                                                                                                                                                                                          |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/)                                                                                                                                                                                                                          |
| abstract   | A specification derived from FIX Latest as the reference standard. It introduces a user-defined `NewOrderSingle` message with customized scenarios for Equity, FX, Sovereign Debt, and Corporate Bond instruments, showcasing the use of scenarios to support a multi-asset class trading interface within a unified specification. |
| source     | [FIX Latest](https://orchestrahub.org/-/fix-latest)                                                                                                                                                                                                                                                                                 |
| rights     | Copyright 2024 Atomic Wire Technology Limited                                                                                                                                                                                                                                                                                       |

## Sections

| Name  | Synopsis                              |
|-------|---------------------------------------|
| Trade | Order handling and execution messages |

## Categories

| Name                       | Section | Component Type | Synopsis |
|----------------------------|---------|----------------|----------|
| SingleGeneralOrderHandling | Trade   | Message        |          |

## Messages

### Message NewOrderSingle type D category SingleGeneralOrderHandling (14)

#### Elaboration

The `base` scenario simplifies the global `NewOrderSingle` message from the FIX reference standard, including only the required elements.

Each message scenario defines a customized `Instrument` component to represent a specific instrument type:
* **CorporateBond**: An `Instrument` component tailored for corporate bond instruments.
* **Equity**: An `Instrument` component tailored for equity instruments.
* **ForeignExchange**: An `Instrument` component tailored for foreign exchange instruments.
* **SovereignDebt**: An `Instrument` component tailored for treasury instruments.

| Sort | Name            | Tag       | Presence | Added   | Documentation                                                                                                                                                     |
|------|-----------------|-----------|----------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader  | component | required | FIX.2.7 | MsgType = D                                                                                                                                                       |
| 2    | ClOrdID         | 11        | required | FIX.2.7 | Unique identifier of the order as assigned by institution or by the intermediary (CIV term, not a hub/service bureau) with closest association with the investor. |
| 3    | Instrument      | component | required | FIX.4.3 | Insert here the set of "Instrument" (symbology) fields defined in "Common Components of Application Messages"                                                     |
| 4    | Side            | 54        | required | FIX.2.7 |                                                                                                                                                                   |
| 5    | TransactTime    | 60        | required | FIX.4.2 | Time this order request was initiated/released by the trader, trading system, or intermediary.                                                                    |
| 6    | OrderQtyData    | component | required | FIX.4.3 |                                                                                                                                                                   |
| 7    | OrdType         | 40        | required | FIX.2.7 |                                                                                                                                                                   |
| 8    | StandardTrailer | component | required | FIX.2.7 |                                                                                                                                                                   |

### Message NewOrderSingle type D scenario Equity (14)

#### Elaboration

This scenario specializes the `NewOrderSingle` message, featuring an `Instrument` component tailored for equities.

| Sort | Name            | Tag       | Presence | Scenario | Added   | Documentation                                                                                                                                                     |
|------|-----------------|-----------|----------|----------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader  | component | required |          | FIX.2.7 | MsgType = D                                                                                                                                                       |
| 2    | ClOrdID         | 11        | required |          | FIX.2.7 | Unique identifier of the order as assigned by institution or by the intermediary (CIV term, not a hub/service bureau) with closest association with the investor. |
| 3    | Instrument      | component | required | Equity   | FIX.4.3 | Insert here the set of "Instrument" (symbology) fields defined in "Common Components of Application Messages"                                                     |
| 4    | Side            | 54        | required |          | FIX.2.7 |                                                                                                                                                                   |
| 5    | TransactTime    | 60        | required |          | FIX.4.2 | Time this order request was initiated/released by the trader, trading system, or intermediary.                                                                    |
| 6    | OrderQtyData    | component | required |          | FIX.4.3 |                                                                                                                                                                   |
| 7    | OrdType         | 40        | required |          | FIX.2.7 |                                                                                                                                                                   |
| 8    | StandardTrailer | component | required |          | FIX.2.7 |                                                                                                                                                                   |

### Message NewOrderSingle type D scenario ForeignExchange (14)

#### Elaboration

This scenario specializes the `NewOrderSingle` message, featuring an `Instrument` component tailored for foreign exchange.

| Sort | Name            | Tag       | Presence | Scenario         | Added   | Documentation                                                                                                                                                     |
|------|-----------------|-----------|----------|------------------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader  | component | required |                  | FIX.2.7 | MsgType = D                                                                                                                                                       |
| 2    | ClOrdID         | 11        | required |                  | FIX.2.7 | Unique identifier of the order as assigned by institution or by the intermediary (CIV term, not a hub/service bureau) with closest association with the investor. |
| 3    | Instrument      | component | required | ForeignExchange  | FIX.4.3 | Insert here the set of "Instrument" (symbology) fields defined in "Common Components of Application Messages"                                                     |
| 4    | Side            | 54        | required |                  | FIX.2.7 |                                                                                                                                                                   |
| 5    | TransactTime    | 60        | required |                  | FIX.4.2 | Time this order request was initiated/released by the trader, trading system, or intermediary.                                                                    |
| 6    | OrderQtyData    | component | required |                  | FIX.4.3 |                                                                                                                                                                   |
| 7    | OrdType         | 40        | required |                  | FIX.2.7 |                                                                                                                                                                   |
| 8    | StandardTrailer | component | required |                  | FIX.2.7 |                                                                                                                                                                   |

### Message NewOrderSingle type D scenario SovereignDebt (14)

#### Elaboration

This scenario specializes the `NewOrderSingle` message, featuring an `Instrument` component tailored for treasuries.

| Sort | Name            | Tag       | Presence | Scenario       | Added   | Documentation                                                                                                                                                     |
|------|-----------------|-----------|----------|----------------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader  | component | required |                | FIX.2.7 | MsgType = D                                                                                                                                                       |
| 2    | ClOrdID         | 11        | required |                | FIX.2.7 | Unique identifier of the order as assigned by institution or by the intermediary (CIV term, not a hub/service bureau) with closest association with the investor. |
| 3    | Instrument      | component | required | SovereignDebt  | FIX.4.3 | Insert here the set of "Instrument" (symbology) fields defined in "Common Components of Application Messages"                                                     |
| 4    | Side            | 54        | required |                | FIX.2.7 |                                                                                                                                                                   |
| 5    | TransactTime    | 60        | required |                | FIX.4.2 | Time this order request was initiated/released by the trader, trading system, or intermediary.                                                                    |
| 6    | OrderQtyData    | component | required |                | FIX.4.3 |                                                                                                                                                                   |
| 7    | OrdType         | 40        | required |                | FIX.2.7 |                                                                                                                                                                   |
| 8    | StandardTrailer | component | required |                | FIX.2.7 |                                                                                                                                                                   |

### Message NewOrderSingle type D scenario CorporateBond (14)

#### Elaboration

This scenario specializes the `NewOrderSingle` message, featuring an `Instrument` component tailored for corporate bonds.

| Sort | Name            | Tag       | Presence | Scenario      | Added   | Documentation                                                                                                                                                     |
|------|-----------------|-----------|----------|---------------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader  | component | required |               | FIX.2.7 | MsgType = D                                                                                                                                                       |
| 2    | ClOrdID         | 11        | required |               | FIX.2.7 | Unique identifier of the order as assigned by institution or by the intermediary (CIV term, not a hub/service bureau) with closest association with the investor. |
| 3    | Instrument      | component | required | CorporateBond | FIX.4.3 | Insert here the set of "Instrument" (symbology) fields defined in "Common Components of Application Messages"                                                     |
| 4    | Side            | 54        | required |               | FIX.2.7 |                                                                                                                                                                   |
| 5    | TransactTime    | 60        | required |               | FIX.4.2 | Time this order request was initiated/released by the trader, trading system, or intermediary.                                                                    |
| 6    | OrderQtyData    | component | required |               | FIX.4.3 |                                                                                                                                                                   |
| 7    | OrdType         | 40        | required |               | FIX.2.7 |                                                                                                                                                                   |
| 8    | StandardTrailer | component | required |               | FIX.2.7 |                                                                                                                                                                   |

## Components

### Component Instrument (1003)

#### Elaboration 

The `base` scenario simplifies the global `Instrument` component from the FIX reference standard, including:
* The set of elements used in all `Instrument` scenarios.
* Custom elements not present in the FIX reference standard.

Each component scenario defines a custom set of elements relevant to a specific instrument type:
* **CorporateBond**: Includes `SecurityIDSource`, `Product` and `SecurityType` fields tailored for corporate bond instruments.
* **Equity**: Includes a `SecurityIDSource` field tailored for equity instruments.
* **ForeignExchange**: Includes a `SecurityType` field tailored for foreign exchange instruments.
* **SovereignDebt**: Includes `SymbolSfx`, `SecurityIDSource`, `Product` and `SecurityType` fields tailored for treasury instruments.

Note that the Corporate Bond scenario introduces two custom fields, `CreditRatingAgency` and `CreditRatingAgencyName`, which are not part of the FIX reference standard. This demonstrates how custom fields can be added to a component from the reference standard, starting from a base scenario. These additions illustrate the flexibility to extend the `Instrument` component when the reference standard lacks the required fields.

| Name                   | Tag   | Presence | Documentation                                                    |
|------------------------|-------|----------|------------------------------------------------------------------|
| Symbol                 | 55    | required |                                                                  |
| SymbolSfx              | 65    | optional |                                                                  |
| SecurityID             | 48    | required |                                                                  |
| SecurityIDSource       | 22    | required |                                                                  |
| Product                | 460   | required |                                                                  |
| SecurityType           | 167   | required |                                                                  |
| SecurityDesc           | 107   | optional |                                                                  |
| CountryOfIssue         | 470   | optional |                                                                  | 
| MaturityDate           | 541   | required |                                                                  |
| Issuer                 | 106   | optional |                                                                  |
| SecurityExchange       | 207   | optional |                                                                  |
| CouponRate             | 223   | required |                                                                  |
| CreditRating           | 255   | required |                                                                  |
| CreditRatingAgency     | 35001 | required |                                                                  |
| CreditRatingAgencyName | 35002 | optional | Conditionally required when CreditRatingAgency(35001)=99 (Other) |

### Component Instrument scenario Equity (1003)

#### Elaboration

This scenario specializes the `Instrument` component specifically for equities.

| Name             | Tag | Presence | Scenario | Documentation                                                                                                                       |
|------------------|-----|----------|----------|-------------------------------------------------------------------------------------------------------------------------------------|
| Symbol           | 55  | required |          | Equity symbol, e.g. IBM, MSFT                                                                                                       |
| SymbolSfx        | 65  | optional |          | Additional information about the stock, e.g. share class identifier, preferred share identifier.                                    |
| SecurityID       | 48  | optional |          | Optional security identifier; source scheme specified in SecurityIDSource. Conditionally required if SecurityIDSource is specified. |
| SecurityIDSource | 22  | optional | Equity   | Conditionally required if SecurityID is specified. Identifies the source scheme of SecurityID value.                                |
| SecurityExchange | 207 | optional |          | Identifies listing exchange of the stock.                                                                                           |

### Component Instrument scenario ForeignExchange (1003)

#### Elaboration

This scenario specializes the `Instrument` component specifically for foreign exchange.

| Name         | Tag | Presence | Scenario        | Documentation                            |
|--------------|-----|----------|-----------------|------------------------------------------|
| Symbol       | 55  | required |                 | The currency pair in the form CCY1/CCY2. |
| SecurityType | 167 | required | ForeignExchange | Identifies type of security.             |

### Component Instrument scenario SovereignDebt (1003)

#### Elaboration

This scenario specializes the `Instrument` component specifically for treasuries.

| Name                   | Tag   | Presence | Scenario       | Documentation                                                                                                         |
|------------------------|-------|----------|----------------|-----------------------------------------------------------------------------------------------------------------------|
| Symbol                 | 55    | required |                | Use [N/A]                                                                                                             |
| SymbolSfx              | 65    | optional | SovereignDebt  | Additional information about the security. For US-issued securities that are re-issued under the same CUSIP.          |
| SecurityID             | 48    | required |                | Security identifier of the source scheme identified in SecurityIDSource.                                              |
| SecurityIDSource       | 22    | required | SovereignDebt  | Source scheme of SecurityID value.                                                                                    |
| Product                | 460   | required | SovereignDebt  | Indicates type of product the security is associated with.                                                            |
| SecurityType           | 167   | required | SovereignDebt  | Type of security.                                                                                                     |
| CountryOfIssue         | 470   | optional |                | Takes 2-char ISO Country code to identify country of issuance. Conditionally required for SecurityType=EUSOV and SOV. |
| MaturityDate           | 541   | required |                | Date of maturity.                                                                                                     |
| Issuer                 | 106   | optional |                | Issuer of the instrument. Conditionally required for SecurityType=EUSOV and SOV.                                      |
| CouponRate             | 223   | required |                | Rate of interest.                                                                                                     |

### Component Instrument scenario CorporateBond (1003)

#### Elaboration

This scenario specializes the `Instrument` component specifically for corporate bonds.

| Name                   | Tag   | Presence | Scenario       | Documentation                                                                                                |
|------------------------|-------|----------|----------------|--------------------------------------------------------------------------------------------------------------|
| Symbol                 | 55    | required |                | Use [N/A]                                                                                                    |
| SecurityID             | 48    | required |                | Security identifier of the source scheme identified in SecurityIDSource.                                     |
| SecurityIDSource       | 22    | required | CorporateBond  | Source scheme of SecurityID value.                                                                           |
| Product                | 460   | required | CorporateBond  | Indicates type of product the security is associated with.                                                   |
| SecurityType           | 167   | required | CorporateBond  | Type of security.                                                                                            |
| SecurityDesc           | 107   | optional |                | Can be used to provide textual description of the instrument.                                                |
| MaturityDate           | 541   | required |                | Maturity date of instrument.                                                                                 |
| Issuer                 | 106   | optional |                | Issuer of the instrument.                                                                                    |
| SecurityExchange       | 207   | optional |                | Identifies listing exchange of the corporate bond.                                                           |
| CouponRate             | 223   | required |                | Rate of interest.                                                                                            |
| CreditRating           | 255   | required |                | Credit rating as assigned by a credit rating agency. Agency is identified in CreditRatingAgency(35001).      |
| CreditRatingAgency     | 35001 | required |                | Identifies the credit rating agency providing the credit rating specified in CreditRating(255).              |
| CreditRatingAgencyName | 35002 | optional |                | Specifies the rating agency's proper name. Conditionally required when CreditRatingAgency(35001)=99 (Other). |

## Fields

| Name                   | Tag   | Type                      | Scenario        | Added   | Description                                                                                                                                                                                             |
|------------------------|-------|---------------------------|-----------------|---------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| SecurityIDSource       | 22    | SecurityIDSourceCodeSet   | CorporateBond   | FIX.2.7 | Identifies class or source of the SecurityID(48) value.                                                                                                                                                 | 
| SecurityIDSource       | 22    | SecurityIDSourceCodeSet   | Equity          | FIX.2.7 | Identifies class or source of the SecurityID(48) value.                                                                                                                                                 | 
| SecurityIDSource       | 22    | SecurityIDSourceCodeSet   | SovereignDebt   | FIX.2.7 | Identifies class or source of the SecurityID(48) value.                                                                                                                                                 | 
| SymbolSfx              | 65    | SymbolSfxCodeSet          | SovereignDebt   | FIX.2.7 | Additional information about the security (e.g. preferred, warrants, etc.). Note also see SecurityType (167)./P/As defined in the NYSE Stock and bond Symbol Directory and in the AMEX Fitch Directory. |
| SecurityType           | 167   | SecurityTypeCodeSet       | CorporateBond   | FIX.4.1 | Indicates type of security. Security type enumerations are grouped by Product(460) field value. NOTE: Additional values may be used by mutual agreement of the counterparties.                          |
| SecurityType           | 167   | SecurityTypeCodeSet       | ForeignExchange | FIX.4.1 | Indicates type of security. Security type enumerations are grouped by Product(460) field value. NOTE: Additional values may be used by mutual agreement of the counterparties.                          |
| SecurityType           | 167   | SecurityTypeCodeSet       | SovereignDebt   | FIX.4.1 | Indicates type of security. Security type enumerations are grouped by Product(460) field value. NOTE: Additional values may be used by mutual agreement of the counterparties.                          |
| Product                | 460   | ProductCodeSet            | CorporateBond   | FIX.4.3 | Indicates the type of product the security is associated with. See also the CFICode (461) and SecurityType (167) fields.                                                                                |
| Product                | 460   | ProductCodeSet            | SovereignDebt   | FIX.4.3 | Indicates the type of product the security is associated with. See also the CFICode (461) and SecurityType (167) fields.                                                                                |
| CreditRatingAgency     | 35001 | CreditRatingAgencyCodeSet |                 |         | Identifies the credit rating agency providing the credit rating specified in CreditRating(255).                                                                                                         |
| CreditRatingAgencyName | 35002 | String                    |                 |         | Specifies the rating agency's proper name.                                                                                                                                                              |

## Codesets

### Codeset SecurityIDSourceCodeSet type String (22)

#### Synopsis

Identifies class or source of the SecurityID(48) value.

| Name           | Value | Id    | Sort | Added      | Synopsis        | Updated    | Updated EP | Added EP | Elaboration |
|----------------|-------|-------|------|------------|-----------------|------------|------------|----------|-------------|
| CUSIP          | 1     | 22001 | 1    | FIX.2.7    | CUSIP           |            |            |          |             |
| SEDOL          | 2     | 22002 | 2    | FIX.2.7    | SEDOL           |            |            |          |             |
| ISINNumber     | 4     | 22004 | 4    | FIX.3.0    | ISIN            | FIX.5.0SP2 | 232        |          |             |
| RICCode        | 5     | 22005 | 5    | FIX.3.0    | RIC             | FIX.5.0SP2 | 232        |          |             |
| ExchangeSymbol | 8     | 22008 | 8    | FIX.4.2    | Exchange symbol | FIX.5.0SP2 | 119        |          |             |

### Codeset SecurityIDSourceCodeSet type String scenario Equity (22)

#### Synopsis

Identifies class or source of the SecurityID(48) value.

| Name       | Value | Id    | Sort | Added      | Synopsis | Updated    | Updated EP | Added EP | Elaboration |
|------------|-------|-------|------|------------|----------|------------|------------|----------|-------------|
| CUSIP      | 1     | 22001 | 1    | FIX.2.7    | CUSIP    |            |            |          |             |
| SEDOL      | 2     | 22002 | 2    | FIX.2.7    | SEDOL    |            |            |          |             |
| ISINNumber | 4     | 22004 | 4    | FIX.3.0    | ISIN     | FIX.5.0SP2 | 232        |          |             |
| RICCode    | 5     | 22005 | 5    | FIX.3.0    | RIC      | FIX.5.0SP2 | 232        |          |             |

### Codeset SecurityIDSourceCodeSet type String scenario SovereignDebt (22)

#### Synopsis

Identifies class or source of the SecurityID(48) value.

| Name       | Value | Id    | Sort | Added      | Synopsis | Updated    | Updated EP | Added EP | Elaboration |
|------------|-------|-------|------|------------|----------|------------|------------|----------|-------------|
| CUSIP      | 1     | 22001 | 1    | FIX.2.7    | CUSIP    |            |            |          |             |
| ISINNumber | 4     | 22004 | 4    | FIX.3.0    | ISIN     | FIX.5.0SP2 | 232        |          |             |

### Codeset SecurityIDSourceCodeSet type String scenario CorporateBond (22)

#### Synopsis

Identifies class or source of the SecurityID(48) value.

| Name           | Value | Id    | Sort | Added      | Synopsis        | Updated    | Updated EP | Added EP | Elaboration |
|----------------|-------|-------|------|------------|-----------------|------------|------------|----------|-------------|
| CUSIP          | 1     | 22001 | 1    | FIX.2.7    | CUSIP           |            |            |          |             |
| SEDOL          | 2     | 22002 | 2    | FIX.2.7    | SEDOL           |            |            |          |             |
| ISINNumber     | 4     | 22004 | 4    | FIX.3.0    | ISIN            | FIX.5.0SP2 | 232        |          |             |
| ExchangeSymbol | 8     | 22008 | 8    | FIX.4.2    | Exchange symbol | FIX.5.0SP2 | 119        |          |             |

### Codeset SecurityTypeCodeSet type String scenario ForeignExchange (167)

#### Synopsis

Indicates type of security. Security type enumerations are grouped by Product(460) field value. NOTE: Additional values may be used by mutual agreement of the counterparties.

| Name                  | Value   | Id     | Sort | Added      | Deprecated | Synopsis                | Group      | Added EP | Deprecated EP | Elaboration | Updated    | Updated EP |
|-----------------------|---------|--------|------|------------|------------|-------------------------|------------|----------|---------------|-------------|------------|------------|
| NonDeliverableForward | FXNDF   | 167019 | 1    | FIX.5.0SP1 |            | Non-deliverable forward | Currency   | 82       |               |             |            |            |
| FXSpot                | FXSPOT  | 167020 | 2    | FIX.5.0SP1 |            | FX Spot                 | Currency   | 82       |               |             |            |            |
| FXForward             | FXFWD   | 167021 | 3    | FIX.5.0SP1 |            | FX Forward              | Currency   | 82       |               |             |            |            |

### Codeset SecurityTypeCodeSet type String scenario SovereignDebt (167)

#### Synopsis

Indicates type of security. Security type enumerations are grouped by Product(460) field value. NOTE: Additional values may be used by mutual agreement of the counterparties.

| Name                  | Value | Id     | Sort | Added      | Deprecated | Synopsis                | Group      | Added EP | Deprecated EP | Elaboration                                                                                          | Updated | Updated EP |
|-----------------------|-------|--------|------|------------|------------|-------------------------|------------|----------|---------------|------------------------------------------------------------------------------------------------------|---------|------------|
| CanadianTreasuryNotes | CAN   | 167071 | 1    | FIX.5.0    |            | Canadian Treasury Notes | Government | 68       |               |                                                                                                      |         |            |
| CanadianTreasuryBills | CTB   | 167072 | 2    | FIX.5.0    |            | Canadian Treasury Bills | Government | 68       |               |                                                                                                      |         |            |
| EuroSovereigns        | EUSOV | 167073 | 3    | FIX.4.4    |            | Euro Sovereigns         | Government |          |               |                                                                                                      |         |            |
| USTreasuryBond        | TBOND | 167076 | 6    | FIX.4.3    |            | US Treasury Bond        | Government |          |               |                                                                                                      |         |            |
| USTreasuryBill        | TBILL | 167078 | 8    | FIX.4.4    |            | US Treasury Bill        | Government |          |               |                                                                                                      |         |            |
| USTreasuryNote        | TNOTE | 167082 | 11   | FIX.4.4    |            | US Treasury Note        | Government |          |               |                                                                                                      |         |            |
| SovereignBond         | SOV   | 167088 | 13   | FIX.Latest |            | Sovereign Bond          | Government | 272      |               | Sovereign or government bond other than Euro and US issuer. Specify sovereign issuer in Issuer(106). |         |            |

### Codeset SecurityTypeCodeSet type String scenario CorporateBond (167)

#### Synopsis

Indicates type of security. Security type enumerations are grouped by Product(460) field value. NOTE: Additional values may be used by mutual agreement of the counterparties.

| Name              | Value  | Id     | Sort | Added      | Deprecated | Synopsis            | Group        | Added EP | Deprecated EP | Elaboration | Updated    | Updated EP |
|-------------------|--------|--------|------|------------|------------|---------------------|--------------|----------|---------------|-------------|------------|------------|
| CorporateBond     | CORP   | 167008 | 0    | FIX.4.1    |            | Corporate Bond      | Corporate    |          |               |             |            |            |
| ConvertibleBond   | CB     | 167010 | 2    | FIX.4.2    |            | Convertible Bond    | Corporate    |          |               |             |            |            |
| EuroCorporateBond | EUCORP | 167012 | 4    | FIX.4.4    |            | Euro Corporate Bond | Corporate    |          |               |             |            |            |

### Codeset SymbolSfxCodeSet type String scenario SovereignDebt (65)

#### Synopsis

Additional information about the security (e.g. preferred, warrants, etc.). Note also see SecurityType (167).
As defined in the NYSE Stock and bond Symbol Directory and in the AMEX Fitch Directory.

| Name       | Value | Id    | Group            | Sort | Added   | Synopsis                                                               |
|------------|-------|-------|------------------|------|---------|------------------------------------------------------------------------|
| WhenIssued | WI    | 65002 | For Fixed Income | 2    | FIX.4.4 | "When Issued" for a security to be reissued under an old CUSIP or ISIN |

### Codeset ProductCodeSet type int scenario SovereignDebt (460)

#### Synopsis

Indicates the type of product the security is associated with. See also the CFICode (461) and SecurityType (167) fields.

| Name        | Value | Id     | Sort | Added   | Synopsis    |
|-------------|-------|--------|------|---------|-------------|
| GOVERNMENT  | 6     | 460006 | 6    | FIX.4.3 | GOVERNMENT  |

### Codeset ProductCodeSet type int scenario CorporateBond (460)

#### Synopsis

Indicates the type of product the security is associated with. See also the CFICode (461) and SecurityType (167) fields.

| Name        | Value | Id     | Sort | Added   | Synopsis    |
|-------------|-------|--------|------|---------|-------------|
| CORPORATE   | 3     | 460003 | 3    | FIX.4.3 | CORPORATE   |

### Codeset CreditRatingAgencyCodeSet type int (35001)

#### Synopsis

Identifies the credit rating agency providing the credit rating specified in CreditRating(255).

| Name   | Value | Synopsis                 |
|--------|-------|--------------------------|
| SAndP  | 1     | Standard & Poor's        |
| Moodys | 2     | Moody's Investor Service |
| Fitch  | 3     | Fitch Ratings            |
| Other  | 99    | Other                    |
