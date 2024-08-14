# Example Custom FIX version 1.0

| Term       | Value                                                                                                      |
|------------|------------------------------------------------------------------------------------------------------------|
| title      | Example Custom FIX spec                                                                                    |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/) |

## Messages

### Message Custom Message type C1 (1)

#### Synopsis

An example Execution Report

| Sort | Name            | Tag   | Presence | Documentation                                    |
|------|-----------------|-------|----------|--------------------------------------------------|
| 1    | StandardHeader  | c     | required | MsgType=8                                        |
| 2    | Account         | 1     | optional | The associated account ID.                       |
| 3    | AvgPx           | 6     | required | Average price of fills.                          |
| 4    | ClOrdID         | 11    | optional | 17-character Order Id sent by the source system. |
| 5    | CumQty          | 14    | required | Total number of shares filled.                   |
| 6    | StandardTrailer | c     | required |                                                  |
