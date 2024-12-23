# Derived Specification Example version 1.0

| Term       | Value                                                                                                                                                                                                                                                                                      |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| title      | Derived Specification Example                                                                                                                                                                                                                                                              |
| creator    | Atomic Wire Technology Limited                                                                                                                                                                                                                                                             |
| created    | 2024-11-01                                                                                                                                                                                                                                                                                 |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/)                                                                                                                                                                                 |
| abstract   | A derived specification based on FIX 4.4 as the reference standard. This specification features a single FIX message incorporating user-defined fields not part of the FIX standard, along with deprecated fields from earlier versions of FIX that are needed for backward compatibility. |
| source     | [FIX.4.4](https://orchestrahub.org/-/fix-4.4)                                                                                                                                                                                                                                              |
| rights     | Copyright 2024 Atomic Wire Technology Limited                                                                                                                                                                                                                                              |

## Messages

### Message NewOrderSingle type D category SingleGeneralOrderHandling (14)

#### Synopsis

This example illustrates how to define a `NewOrderSingle` message as part of a derived specification.

Users only need to specify the desired elements for the message and provide details for any custom fields.

In this example, most fields and components follow the standard from the reference specification (FIX 4.4). The customisations include:
* Setting `Account` as a required field.
* Modifying the `Instrument` component to better fit securities instruments.
* Including the `Rule80A` field, which was replaced by `OrderCapacity` (tag 528) in FIX 4.3.
* Including the `ExecBroker` field, which was deprecated in FIX 4.3.
* Reordering the fields.

| Sort | Name                  | Tag       | Presence | Added   | Documentation                                                                                                          |
|------|-----------------------|-----------|----------|---------|------------------------------------------------------------------------------------------------------------------------|
| 1    | StandardHeader        | component | required | FIX.2.7 | MsgType=D                                                                                                              |
| 2    | Account               | 1         | required | FIX.2.7 | **Custom Presence**: `Account` is an optional field in FIX 4.4.                                                        |
| 3    | ClOrdID               | 11        | required | FIX.2.7 |                                                                                                                        |
| 4    | Currency              | 15        | optional | FIX.2.7 |                                                                                                                        |
| 5    | ExecInst              | 18        | optional | FIX.2.7 |                                                                                                                        |
| 6    | HandlInst             | 21        | optional | FIX.2.7 |                                                                                                                        |
| 7    | Instrument            | component | required | FIX.4.3 | **Custom Component**: `Instrument` is custom component that defines a `SecurityID` and a `SecurityIDSource`.           |
| 8    | OrderQtyData          | component | required | FIX.4.3 |                                                                                                                        |
| 9    | OrdType               | 40        | required | FIX.2.7 |                                                                                                                        |
| 10   | Price                 | 44        | optional | FIX.2.7 |                                                                                                                        |
| 11   | Rule80A               | 47        | required | FIX.2.7 | **Custom Field**: `Rule80A` is from FIX 4.2. Replaced by OrderCapacity (tag 528) as of FIX.4.3.                        |
| 12   | Side                  | 54        | required | FIX.2.7 |                                                                                                                        |
| 13   | TimeInForce           | 59        | optional | FIX.2.7 |                                                                                                                        |
| 14   | TransactTime          | 60        | required | FIX.4.2 |                                                                                                                        |
| 15   | ExecBroker            | 76        | optional | FIX.2.7 | **Custom Field**: `ExecBroker` is from FIX 4.2. Deprecated as of FIX.4.3.                                              |
| 16   | StopPx                | 99        | optional | FIX.2.7 |                                                                                                                        | 
| 17   | ExDestination         | 100       | optional | FIX.2.7 |                                                                                                                        | 
| 18   | MinQty                | 110       | optional | FIX.2.7 |                                                                                                                        | 
| 19   | MaxFloor              | 111       | optional | FIX.2.7 |                                                                                                                        | 
| 20   | LocateReqd            | 114       | optional | FIX.4.0 |                                                                                                                        | 
| 21   | SettlCurrency         | 120       | optional | FIX.4.0 |                                                                                                                        | 
| 22   | ExpireTime            | 126       | optional | FIX.4.0 |                                                                                                                        | 
| 23   | ComplianceID          | 376       | optional | FIX.4.2 |                                                                                                                        | 
| 24   | SolicitedFlag         | 377       | optional | FIX.4.2 |                                                                                                                        | 
| 25   | SecondaryClOrdID      | 526       | optional | FIX.4.3 |                                                                                                                        | 
| 26   | CorporateBuyback      | 5001      | optional |         | **Custom Field**: `CorporateBuyback` is a user-defined field (UDF) that is not present in the reference specification. |
| 27   | StandardTrailer       | component | required | FIX.2.7 |                                                                                                                        | 

## Components

### Component Instrument category Common (1003)

#### Synopsis

The Instrument component block contains all the fields commonly used to describe a security or instrument.

|       Name       | Tag | Presence |                       Documentation                       |
|------------------|-----|----------|-----------------------------------------------------------|
| SecurityID       | 48  | required | Identifies the security being traded.                     |
| SecurityIDSource | 22  | required | Identifies the class or source of the security e.g. ISIN. |

## Fields

| Name             | Tag  | Type                    | Synopsis                                                                                  |
|------------------|------|-------------------------|-------------------------------------------------------------------------------------------|
| Rule80A          | 47   | Rule80ACodeSet          | This field is not present in the reference specification as it was replaced in FIX 4.3.   |
| ExecBroker       | 76   | String                  | This field is not present in the reference specification as it was deprecated in FIX 4.3. |
| CorporateBuyback | 5001 | CorporateBuybackCodeSet | A user-defined field (UDF) that is not included in the reference specification.           |

### Codesets

### Codeset SecurityIDSourceCodeSet type String (22)

#### Synopsis

Identifies class or source of the security.

|   Name   | Value | Id    | Sort |                                                                   Synopsis                                                                   | Elaboration |
|----------|-------|-------|------|----------------------------------------------------------------------------------------------------------------------------------------------|-------------|
| ISIN     | 1     | 22001 | 1    | International Securities Identification Number (ISIN) conforming to [ISO 6166](https://www.iso.org/standard/78502.html).                     |             |
| RIC      | 2     | 22002 | 2    | Refinitiv Instrument Code (previously Reuters Instrument Code), or RIC, is a ticker code used to identify financial instruments and indices. |             |
| Exchange | 3     | 22003 | 3    | Exchange symbol                                                                                                                              |             |

### Codeset Rule80ACodeSet type char (47)

#### Synopsis

Note that the name of this field changed to "`OrderCapacity`" as `Rule80A` is a very US market-specific term.

| Name                                      | Value | Id    | Added   | Sort | Synopsis                                                                                                                            | 
|-------------------------------------------|-------|-------|---------|------|-------------------------------------------------------------------------------------------------------------------------------------|
| AgencySingleOrder                         | A     | 47001 | FIX.2.7 | 1    | Agency single order                                                                                                                 |
| ShortExemptTransactionAType               | B     | 47002 | FIX.4.1 | 2    | Short exempt transaction (refer to A type)                                                                                          |
| ProprietaryNonAlgo                        | C     | 47003 | FIX.2.7 | 3    | Program Order, non-index arb, for Member firm/org                                                                                   |
| ProgramOrderMember                        | D     | 47004 | FIX.2.7 | 4    | Program Order, index arb, for Member firm/org                                                                                       |
| ShortExemptTransactionForPrincipal        | E     | 47005 | FIX.4.1 | 5    | Registered Equity Market Maker trades                                                                                               |
| ShortExemptTransactionWType               | F     | 47006 | FIX.4.1 | 6    | Short exempt transaction (refer to W type)                                                                                          |
| ShortExemptTransactionIType               | H     | 47007 | FIX.4.1 | 7    | Short exempt transaction (refer to I type)                                                                                          |
| IndividualInvestor                        | I     | 47008 | FIX.2.7 | 8    | Individual Investor, single order                                                                                                   |
| ProprietaryAlgo                           | J     | 47009 | FIX.2.7 | 9    | Program Order, index arb, for individual customer                                                                                   |
| AgencyAlgo                                | K     | 47010 | FIX.2.7 | 10   | Program Order, non-index arb, for individual customer                                                                               |
| ShortExemptTransactionMemberAffiliated    | L     | 47011 | FIX.4.1 | 11   | Short exempt transaction for member competing market-maker affiliated with the firm clearing the trade (refer to P and O types)     |
| ProgramOrderOtherMember                   | M     | 47012 | FIX.2.7 | 12   | Program Order, index arb, for other member                                                                                          |
| AgentForOtherMember                       | N     | 47013 | FIX.2.7 | 13   | Program Order, non-index arb, for other member                                                                                      |
| ProprietaryTransactionAffiliated          | O     | 47014 | FIX.4.1 | 14   | Competing dealer trades                                                                                                             |
| Principal                                 | P     | 47015 | FIX.4.1 | 15   | Principal                                                                                                                           |
| TransactionNonMember                      | R     | 47016 | FIX.4.1 | 16   | Competing dealer trades                                                                                                             |
| SpecialistTrades                          | S     | 47017 | FIX.4.1 | 17   | Specialist trades                                                                                                                   |
| TransactionUnaffiliatedMember             | T     | 47018 | FIX.4.1 | 18   | Competing dealer trades                                                                                                             |
| AgencyIndexArb                            | U     | 47019 | FIX.2.7 | 19   | Program Order, index arb, for other agency                                                                                          |
| AllOtherOrdersAsAgentForOtherMember       | W     | 47020 | FIX.2.7 | 20   | All other orders as agent for other member                                                                                          |
| ShortExemptTransactionMemberNotAffiliated | X     | 47021 | FIX.4.1 | 21   | Short exempt transaction for member competing market-maker not affiliated with the firm clearing the trade (refer to W and T types) |
| AgencyNonAlgo                             | Y     | 47022 | FIX.2.7 | 22   | Program Order, non-index arb, for other agency                                                                                      |
| ShortExemptTransactionNonMember           | Z     | 47023 | FIX.4.1 | 23   | Short exempt transaction for non-member competing market-maker (refer to A and R types)                                             |

### Codeset CorporateBuybackCodeSet type Boolean (5001)

#### Synopsis

Instruction related to closing auctions.

| Name         | Value | Id      | Sort | Synopsis                        |
|--------------|-------|---------|------|---------------------------------|
| Permitted    | Y     | 5001001 | 1    | Corporate buyback permitted     |
| NotPermitted | N     | 5001002 | 2    | Corporate buyback not permitted |
