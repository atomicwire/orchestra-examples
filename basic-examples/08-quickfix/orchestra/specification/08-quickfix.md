# Example Custom FIX version 1.0

| Term       | Value                                                                                                      |
|------------|------------------------------------------------------------------------------------------------------------|
| title      | Example Custom FIX spec                                                                                    |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/) |

## Messages

### Message NewOrderSingle Message type D (14)

#### Synopsis

A redefined example of the NewOrderSingle message. Account has been made a required field, and an optional field RoutingID added

| Sort | Name            | Tag | Presence | Documentation                                 |
|------|-----------------|-----|----------|-----------------------------------------------|
| 1    | StandardHeader  | c   | required | MsgType=D                                     |
| 2    | ClOrdID         | 11  | required | The client order ID                           |
| 3    | Account         | 1   | required | The account is required for this derived spec |
| 4    | Side            | 54  | required | Side of the order                             |
| 5    | TransactTime    | 60  | required | The time of this transaction                  |
| 6    | OrdType         | 40  | required | The type of the order - buy or sell           |
| 7    | Instrument      | c   | required | Instrument details                            |
| 8    | OrderQtyData    | c   | required | The amount or quantity of the order           |
| 9    | RoutingID       | 217 | optional | Optional route to a specific destination      |
| 10   | StandardTrailer | c   | required |                                               |

