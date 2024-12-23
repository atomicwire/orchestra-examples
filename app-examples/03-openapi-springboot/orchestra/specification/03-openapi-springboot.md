# Custom Instrument Example version 1.0

|    Term    |                                                                                   Value                                                                                    |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| title      | Custom Instrument Example                                                                                                                                                  |
| creator    | Atomic Wire Technology Limited                                                                                                                                             |
| created    | 2024-11-01                                                                                                                                                                 |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/)                                                                 |
| abstract   | A derived specification based on FIX Latest as the reference standard. This specification features a single FIX message incorporating a user-defined instrument component. |
| source     | [FIX Latest](https://orchestrahub.org/-/fix-latest)                                                                                                                        |
| rights     | Copyright 2024 Atomic Wire Technology Limited                                                                                                                              |

## Messages

### Message NewOrderSingle type D category SingleGeneralOrderHandling (14)

#### Synopsis

An example Execution Report

| Sort |    Name    | Tag | Presence | Documentation |
|------|------------|-----|----------|---------------|
| 1    | Instrument | c   | required |               |

## Components

### Component Instrument category Common (1003)

|       Name       | Tag  | Presence |
|------------------|------|----------|
| Symbol           | 55   | required |
| SecurityID       | 48   | required |
| SecurityIDSource | 22   | required |
| Product          | 460  | required |
| CFICode          | 461  |          |
| UPICode          | 2891 |          |

## Codesets

### Codeset SecurityIDSourceCodeSet type String (22)

#### Synopsis

Identifies class or source of the SecurityID(48) value.

|    Name    | Value | Synopsis |
|------------|-------|----------|
| CUSIP      | 1     | CUSIP    |
| SEDOL      | 2     | SEDOL    |
| ISINNumber | 4     | ISIN     |

### Codeset ProductCodeSet type String (460)

#### Synopsis

Indicates the type of product the security is associated with.

|   Name    | Value | Synopsis  |
|-----------|-------|-----------|
| AGENCY    | 1     | AGENCY    |
| COMMODITY | 2     | COMMODITY |
| CURRENCY  | 4     | CURRENCY  |
| EQUITY    | 5     | EQUITY    |

