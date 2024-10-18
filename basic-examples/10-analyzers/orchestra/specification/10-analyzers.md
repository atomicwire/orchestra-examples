# Example Analyzer Custom FIX version 1.0

| Term       | Value                                                                                                      |
|------------|------------------------------------------------------------------------------------------------------------|
| title      | Example Analyzer Custom FIX spec                                                                           |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/) |
| issued     | 2024-10-16                                                                                                 |

## Messages

### Message Custom Message type C1 (1)

#### Synopsis

An example message that will raise multiple warnings from the Specification Analyzers. 

| Sort | Name                     | Tag  | Presence | Documentation                                  |
|------|--------------------------|------|----------|------------------------------------------------|
| 1    | StandardHeader           | c    | required | MsgType=C1                                     |
| 2    | capitalizationExample    | 5211 | optional | Field names must be capitalised for FIX        |
| 3    | MissingTypeExample       | 5212 | optional | Field is missing type information              |
| 4    | capitalizationExample    | 5211 | optional | Duplicate field name in message                |
| 5    | DataMissingLengthExample | 5213 | optional | Data type field missing preceding Length field |
| 6    | ClOrdID                  | 11   | required | The client order ID                            |
| 7    | Account                  | 1    | required | The account ID                                 |
| 8    | Side                     | 54   | required | Side of the order                              |
| 9    | TransactTime             | 60   | required | The time of this transaction                   |
| 10   | OrdType                  | 40   | required | The type of the order - buy or sell            |
| 11   | Instrument               | c    | required | Instrument details                             |
| 12   | StandardTrailer          | c    | required |                                                |


## Fields

| Name                     | Tag  | Type   | Description                                 |
|--------------------------|------|--------|---------------------------------------------|
| capitalizationExample    | 5211 | String | Field to demonstrate analyzer functionality |
| dataMissingLengthExample | 5213 | data   | Field to demonstrate analyzer functionality |
