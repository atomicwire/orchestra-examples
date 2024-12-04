# Verification Example version 1.0

| Term       | Value                                                                                                                                                                    |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| title      | Verification Example                                                                                                                                                     |
| creator    | Atomic Wire Technology Limited                                                                                                                                           |
| created    | 2024-11-01                                                                                                                                                               |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/)                                                               |
| abstract   | A derived specification based on FIX Latest as the reference standard. This specification incorporates multiple errors in order to demonstrate validation functionality. |
| source     | [FIX Latest](https://orchestrahub.org/-/fix-latest)                                                                                                                      |
| rights     | Copyright 2024 Atomic Wire Technology Limited                                                                                                                            |

## Sections

| Name                  | Synopsis |
|-----------------------|----------|
| SyntaxValidation      |          |
| SemanticValidation    |          |
| EncodingCompatibility |          |
| MetadataValidation    |          |

## Categories

| Name                       | Section               | Component Type | Synopsis                                                                                                           |
|----------------------------|-----------------------|----------------|--------------------------------------------------------------------------------------------------------------------|
| XmlSchemaValidation        | SyntaxValidation      | Message        |                                                                                                                    |
| IntegrityChecking          | SemanticValidation    | Message        |                                                                                                                    |
| FixStyleChecking           | EncodingCompatibility | Message        |                                                                                                                    |
| FixTagValueCompatibility   | EncodingCompatibility | Message        |                                                                                                                    |
| SpecificationMetadata      | MetadataValidation    | Message        |                                                                                                                    |
| DocumentationMetadata      | MetadataValidation    | Message        |                                                                                                                    |
| CategoryWithUnknownSection | Unknown               | Message        | **SectionRefException**: Category CategoryWithUnknownSectionAndCategoryType refers to non-existing section Unknown |
| CategoryWithMissingSection |                       | Message        |                                                                                                                    |


## Messages

### Message XmlSchemaValidationErrors type U1 category XmlSchemaValidation (1)

#### Synopsis

A custom message that demonstrates XML Schema validation errors.

| Sort | Name                            | Tag   | Scenario | Presence | Documentation                                                                                                                                             |
|------|---------------------------------|-------|----------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader                  | c     |          | required | MsgType=U1                                                                                                                                                |
| 2    | FieldWithUnknownType            | 5101  |          | required | **SAXParseException**: lineNumber: 1838; columnNumber: 60; cvc-complex-type.4: Attribute 'type' must appear on element 'fixr:field'.                      |
| 3    | ScenarioVariantsWithSameNameGrp | g     | Variant  | required | **SAXParseException**: Duplicate key value [ScenarioVariantsWithSameNameGrp,Variant] declared for identity constraint "groupNameKey" of element "groups". |
| 4    | StandardTrailer                 | c     |          | required |                                                                                                                                                           |

### Message IntegrityCheckingErrors type U2 category IntegrityChecking (2)

#### Synopsis

A custom message that demonstrates integrity checking errors.

| Sort | Name                            | Tag | Scenario  | Presence | Documentation                                                                                                                                                           |
|------|---------------------------------|-----|-----------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader                  | c   |           | required | MsgType=U2                                                                                                                                                              |
| 2    | ScenarioElementsNotSubsetGrp    | g   | NotSubset | required | **RefsMissingInBaseScenarioException**: Group ScenarioElementsNotSubsetGrp (1) scenario 'NotSubset' contained Field refs that were not found in the base scenario: 5004 |
| 3    | StandardTrailer                 | c   |           | required |                                                                                                                                                                         |

### Message FixStyleCheckingErrors type X3 category FixStyleChecking (3)

#### Synopsis

A custom message that demonstrates FIX style checking errors.

| Sort | Name                     | Tag  | Presence | Documentation                                                                                                                                             |
|------|--------------------------|------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader           | c    | required | **CustomMsgTypePrefixException**: Custom MsgType must begin with 'U': FixStyleCheckingErrors (1)                                                          |
| 2    | namesAreCapitalized      | 5301 | required | **UncapitalizedNameException**: Name or abbrName did not start with a capital letter: namesAreCapitalized                                                 |
| 3    | BooleanCodeValuesAreYN   | 5302 | required | **InvalidCodesetException**: Expected Boolean codeset BooleanCodeValuesAreYNCodeSet to have values Y, N, but found values true, false                     |
| 4    | CharCodeValuesValid      | 5303 | required | **InvalidCodesetException**: Expected char codeset CharCodeValuesValidCodeSet to have only single-character values, but found value AA                    |
| 5    | StringCodeValuesValid    | 5304 | required | **InvalidCodesetException**: Expected String codeset StringCodeValuesValidCodeSet values to be non-empty and contain no whitespace, but found value 'A A' |
| 6    | IntCodeValuesValid       | 5305 | required | **InvalidCodesetException**: Expected int codeset IntCodeValuesValidCodeSet to have only positive integer values, but found value A                       |
| 7    | StandardTrailer          | c    | required |                                                                                                                                                           |

### Message FixTagValueCompatibilityErrors type U4 category FixTagValueCompatibility (4)

#### Synopsis

A custom message that demonstrates FIX tagvalue compatibility errors.

| Sort | Name                                                   | Tag  | Scenario   | Presence | Documentation                                                                                                                                                                                                                                                                   |
|------|--------------------------------------------------------|------|------------|----------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader                                         | c    |            | required | **MissingRequiredHeaderOrTrailerFieldException**: Field at position 31 must be Checksum (10), but was FieldOfTypeDataOrXmlDataNotPrecededByFieldOfTypeLength (5102)                                                                                                             |
| 2    | DuplicateFieldInMessage                                | 5401 |            | required |                                                                                                                                                                                                                                                                                 |
| 3    | DuplicateFieldInMessage                                | 5401 |            | required | **DuplicateFieldInMessageException**: Duplicate fields in message FixTagValueCompatibilityErrors (4): DuplicateFieldInMessage (5401)                                                                                                                                            |
| 4    | FieldOfTypeDataOrXmlDataNotPrecededByFieldOfTypeLength | 5402 |            | required | **DataFieldNotFollowedByLengthFieldException**: Data field FieldOfTypeDataOrXmlDataNotPrecededByFieldOfTypeLength (5402) in message FixTagValueCompatibilityErrors (4) should be preceded by a field of type Length, but was instead preceded by DuplicateFieldInMessage (5401) |
| 5    | ScenarioElementsNotOrderedGrp                          | g    | NotOrdered | required | **ScenarioFieldsNotOrderedException**: Data field FieldA (5103) in group ScenarioElementsNotOrderedGrp (5) in scenario NotOrdered is not in the correct order when compared with the base scenario ScenarioElementsNotOrderedGrp (5)                                            |

## Groups

### Group ScenarioVariantsWithSameNameGrp scenario base (1)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldA     | 5001 | required |
| 3    | FieldB     | 5002 | required |
| 4    | FieldC     | 5003 | optional |

### Group ScenarioVariantsWithSameNameGrp scenario Variant (1)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldA     | 5001 | required |
| 3    | FieldB     | 5002 | required |

### Group ScenarioVariantsWithSameNameGrp scenario Variant (1)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldB     | 5002 | required |
| 3    | FieldC     | 5003 | required |

### Group ScenarioElementsNotSubsetGrp scenario base (2)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldA     | 5001 | required |
| 3    | FieldB     | 5002 | required |
| 4    | FieldC     | 5003 | optional |

### Group ScenarioElementsNotSubsetGrp scenario NotSubset (2)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldA     | 5001 | required |
| 3    | FieldB     | 5002 | required |
| 4    | FieldC     | 5003 | optional |
| 5    | FieldD     | 5004 | optional |

### Group ScenarioElementsNotOrderedGrp scenario base (4)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldA     | 5001 | required |
| 3    | FieldB     | 5002 | required |
| 4    | FieldC     | 5003 | optional |

### Group ScenarioElementsNotOrderedGrp scenario Ordered (4)

#### Synopsis

| Sort | Name       | Tag  | Presence |
|------|------------|------|----------|
| 1    | NoOfGroups | 5000 | optional |
| 2    | FieldA     | 5001 | required |
| 3    | FieldB     | 5002 | required |

### Group ScenarioElementsNotOrderedGrp scenario NotOrdered (4)

#### Synopsis

| Sort | Name        | Tag  | Presence |
|------|-------------|------|----------|
| 1    | NoOfGroups  | 5000 | optional |
| 2    | FieldB      | 5002 | required |
| 3    | FieldA      | 5001 | required |

## Fields

| Name                                                   | Tag  | Type                          | Description |
|--------------------------------------------------------|------|-------------------------------|-------------|
| NoOfGroups                                             | 5000 | NumInGroup                    |             |
| FieldA                                                 | 5001 | String                        |             |
| FieldB                                                 | 5002 | String                        |             |
| FieldC                                                 | 5003 | String                        |             |
| FieldD                                                 | 5004 | String                        |             |
| namesAreCapitalized                                    | 5301 | String                        |             |
| BooleanCodeValuesAreYN                                 | 5302 | BooleanCodeValuesAreYNCodeSet |             |
| CharCodeValuesValid                                    | 5303 | CharCodeValuesValidCodeSet    |             |
| StringCodeValuesValid                                  | 5304 | StringCodeValuesValidCodeSet  |             |
| IntCodeValuesValid                                     | 5305 | IntCodeValuesValidCodeSet     |             |
| DuplicateFieldInMessage                                | 5401 | String                        |             |
| FieldOfTypeDataOrXmlDataNotPrecededByFieldOfTypeLength | 5402 | data                          |             |

## Codesets

### Codeset BooleanCodeValuesAreYNCodeSet type Boolean (5302)

#### Synopsis

| Name | Value | Synopsis |
|------|-------|----------|
| Yes  | true  |          |
| No   | false |          |

### Codeset CharCodeValuesValidCodeSet type char (5303)

#### Synopsis

| Name            | Value | Synopsis |
|-----------------|-------|----------|
| InvalidCharCode | AA    |          |

### Codeset StringCodeValuesValidCodeSet type String (5304)

#### Synopsis

| Name                            | Value | Synopsis |
|---------------------------------|-------|----------|
| InvalidStringCodeWithWhitespace | A A   |          |

### Codeset IntCodeValuesValidCodeSet type int (5305)

#### Synopsis

| Name           | Value | Synopsis |
|----------------|-------|----------|
| InvalidIntCode | A     |          |

## Datatypes

| Name       | Synopsis                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Example | Standard | Base | Pattern | Mininclusive |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|----------|------|---------|--------------|
| Boolean    | char field containing one of two values:/P/'Y' = True/Yes/P/'N' = False/No                                                                                                                                                                                                                                                                                                                                                                                                                                    |         |          |      |         |              |
| char       | Single character value, can include any alphanumeric character or punctuation except the delimiter. All char fields are case sensitive (i.e. m != M).                                                                                                                                                                                                                                                                                                                                                         |         |          |      |         |              |
| data       | string field containing raw data with no format or content restrictions. Data fields are always immediately preceded by a length field. The length field should specify the number of bytes of the value of the data field (up to but not including the terminating SOH)./P/Caution: the value of one of these fields may contain the delimiter (SOH) character. Note that the value specified for this field should be followed by the delimiter (SOH) character as all fields are terminated with an "SOH". |         |          |      |         |              |
| int        | Sequence of digits without commas or decimals and optional sign character (ASCII characters "-" and "0" - "9" ). The sign character utilizes one byte (i.e. positive int is "99999" while negative int is "-99999"). Note that int values may contain leading zeros (e.g. "00023" = "23").                                                                                                                                                                                                                    |         |          |      |         |              |
| NumInGroup | int field representing the number of entries in a repeating group. Value must be positive.                                                                                                                                                                                                                                                                                                                                                                                                                    |         |          |      |         |              |
| String     | Alpha-numeric free format strings, can include any character or punctuation except the delimiter. All String fields are case sensitive (i.e. morstatt != Morstatt).                                                                                                                                                                                                                                                                                                                                           |         |          |      |         |              |
