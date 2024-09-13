# My Custom Spec version 1.0

|    Term    |                                                   Value                                                    |
|------------|------------------------------------------------------------------------------------------------------------|
| title      | My Custom Spec                                                                                             |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/) |

## Messages

### Message QuickstartDemo Message type C1 (1)

#### Synopsis

An update to an order book.

| Sort |    Name    | Tag | Presence |             Documentation             |
|------|------------|-----|----------|---------------------------------------|
| 1    | timestamp  | 1   | required | The microsecond-resolution timestamp. |
| 2    | instrument | 2   | required | The instrument identifier.            |
| 3    | price      | 3   | required | The price.                            |

## Fields

|    Name    | Tag |   Type    | Synopsis | Elaboration |
|------------|-----|-----------|----------|-------------|
| timestamp  | 1   | timestamp |          |             |
| instrument | 2   | string    |          |             |
| price      | 3   | double    |          |             |

## Datatypes

|   Name    |                                               Synopsis                                               |            Example             |
|-----------|------------------------------------------------------------------------------------------------------|--------------------------------|
| timestamp | An instant in time. SHOULD be encoded as an ISO 8601-encoded ASCII string with a zone offset of 'Z'. | 2023-11-13T16:37:09.974402382Z |
| string    | A Unicode string. SHOULD be encoded using UTF-8.                                                     | Hello, world!                  |
| double    | A double-precision floating-point value compatible with IEEE 754.                                    | 123.456                        |

