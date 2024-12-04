# Order Book Example version 1.0

|    Term    |                                                                                                                                                                                                                                                                                                                                                                             Value                                                                                                                                                                                                                                                                                                                                                                              |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| title      | Order Book Example                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| creator    | Atomic Wire Technology Limited                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| created    | 2024-11-01                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| conformsTo | [Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| abstract   | An order book is an electronic list of buy and sell orders for a particular financial instrument, organised by price level, that shows the available quantities of the instrument at each price point. It provides a real-time view of market depth and liquidity, helping participants understand supply and demand dynamics, and is commonly used in exchanges to facilitate transparent and efficient trading. This example specification defines a set of messages for the effective management of an order book, including messages for adding, modifying, and canceling orders. It also includes messages for reporting key market statistics, such as volume, price levels, and order book depth, to provide insights into market trends and liquidity. |
| rights     | Copyright 2024 Atomic Wire Technology Limited                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |

## Sections

|   Name   |                                                                        Synopsis                                                                        |
|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| PreTrade | Analysis and decision-making activities conducted before executing a financial trade, including market research, price discovery, and risk assessment. |

## Categories

|    Name    | Section  | Component Type |                                                                    Synopsis                                                                     |
|------------|----------|----------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| MarketData | PreTrade | Message        | Real-time or historical information on asset prices, trade volumes, and related statistics that help investors make informed trading decisions. |

## Messages

### Message AddOrder Message type A category MarketData (1)

#### Synopsis

The AddOrder message is used to add a new order to the order book. This message typically includes details such as the order's price, quantity, order type (e.g., limit or market), side (buy or sell), and a unique order identifier. When processed, the AddOrder message updates the order book by placing the new order at the specified price level, affecting the book's structure and potentially impacting price discovery and market depth.

| Sort |      Name      |    Tag    | Presence |                        Documentation                         |
|------|----------------|-----------|----------|--------------------------------------------------------------|
| 1    | MessageType    |           | required | A=Add Order                                                  |
| 2    | Timestamp      |           | required | Time the message was generated.                              |
| 3    | OrderID        |           | required | Unique identifier of the order.                              |
| 4    | Side           |           | required | Side of the order.                                           |
| 5    | Size           |           | required | Displayed size of the order.                                 |
| 6    | Instrument     | component | required | Instrument identifier.                                       |
| 7    | Price          |           | required | Limit price of the order.                                    |
| 8    | ExecutionVenue |           | required | Venue from which market data is received for the instrument. |
| 9    | OrderBookType  |           | required | Type of order book.                                          |
| 10   | OrderType      |           | required | Type of order placed e.g. Limit order.                       |
| 11   | Participant    |           | required | Identity of trading participant that submitted the order.    |

### Message ModifyOrder type M category MarketData (2)

#### Synopsis

The ModifyOrder message is used to update an order’s price and/or size on the order book.

| Sort |       Name       |    Tag    | Presence |                        Documentation                         |
|------|------------------|-----------|----------|--------------------------------------------------------------|
| 1    | MessageType      |           | required | M=Modify Order                                               |
| 2    | Timestamp        |           | required | Time the message was generated.                              |
| 3    | OrderID          |           | required | Unique identifier of the order.                              |
| 4    | Instrument       | component | required | Instrument identifier.                                       |
| 5    | Side             |           | required | Side of the order.                                           |
| 6    | OrderBookType    |           | required | Type of order book.                                          |
| 7    | NewQuantity      |           | required | New displayed quantity of the order.                         |
| 8    | NewPrice         |           | required | New price of the order.                                      |
| 9    | ExecutionVenue   |           | required | Venue from which market data is received for the instrument. |
| 10   | PreviousPrice    |           | required | Previous price of the order.                                 |
| 11   | PreviousQuantity |           | required | Previous displayed quantity of the order.                    |

### Message DeleteOrder type D category MarketData (3)

#### Synopsis

The DeleteOrder message is used to remove an order from the order book.

| Sort |       Name       |    Tag    | Presence |                        Documentation                         |
|------|------------------|-----------|----------|--------------------------------------------------------------|
| 1    | MessageType      |           | required | D=Delete Order                                               |
| 2    | Timestamp        |           | required | Time the message was generated                               |
| 3    | OrderID          |           | required | Unique identifier of the order.                              |
| 4    | Instrument       | component | required | Instrument identifier.                                       |
| 5    | Side             |           | required | Side of the order.                                           |
| 6    | OrderBookType    |           | required | Type of order book.                                          |
| 7    | ExecutionVenue   |           | required | Venue from which market data is received for the instrument. |
| 8    | PreviousPrice    |           | required | Price of the order that was deleted from the book.           |
| 9    | PreviousQuantity |           | required | Quantity of the order that was deleted from the book.        |

### Message Trade type T category MarketData (4)

#### Synopsis

The Trade message is used to report the full or partial execution of an order in the market.

| Sort |      Name       |    Tag    | Presence |                                                                          Documentation                                                                           |
|------|-----------------|-----------|----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | MessageType     |           | required | T=Trade                                                                                                                                                          |
| 2    | Timestamp       |           | required | Time the message was generated.                                                                                                                                  |
| 3    | TransactionTime |           | required | Execution timestamp as reported by the supported market. If a trade is cancelled or amended, this field will contain the transaction time of the original trade. |
| 4    | ExecutionVenue  |           | required | Venue from which market data is received for the instrument.                                                                                                     |
| 5    | ExecutedSize    |           | required | Total executed quantity.                                                                                                                                         |
| 6    | Instrument      | component | required | Instrument identifier.                                                                                                                                           |
| 7    | Price           |           | required | Executed price.                                                                                                                                                  |
| 8    | TradeID         |           | required | Unique identifier of the trade.                                                                                                                                  |
| 9    | TradeType       |           | required | Type of trade executed.                                                                                                                                          |

### Message Statistics type S category MarketData (5)

#### Synopsis

The Statistics message provides aggregated statistical information about trading activity for a specific asset over a defined period, typically including metrics such total volume, average price, and volatility. This message is used to give traders and analysts a summary of recent market conditions and trends, supporting more informed trading and risk management decisions.

| Sort |      Name      |    Tag    | Presence |                        Documentation                         |
|------|----------------|-----------|----------|--------------------------------------------------------------|
| 1    | MessageType    |           | required | S=Statistics                                                 |
| 2    | Timestamp      |           | required | Time the message was generated.                              |
| 3    | Instrument     | component | required | Instrument identifier.                                       |
| 4    | ExecutionVenue |           | required | Venue from which market data is received for the instrument. |
| 5    | Volume         |           | required | Cumulative volume of all trades for the trading day.         |
| 6    | VWAP           |           | required | Volume weighted average price for the day for all trades.    |
| 7    | NumberOfTrades |           | required | Count of all trades for the day.                             |

### Message Analytics type a category MarketData (6)

#### Synopsis

The Analytics message is used to disseminate additional statistics including order book activity statistics.

| Sort |          Name          |    Tag    | Presence |                                 Documentation                                  |
|------|------------------------|-----------|----------|--------------------------------------------------------------------------------|
| 1    | MessageType            |           | required | a=Analytics                                                                    |
| 2    | Timestamp              |           | required | Time the message was generated.                                                |
| 3    | Instrument             | component | required | Instrument identifier.                                                         |
| 4    | ExecutionVenue         |           | required | Please refer to the Additional Field Values section for valid values.          |
| 5    | StartTime              |           | required | Time the calculation of the statistics on this message began.                  |
| 6    | EndTime                |           | required | Time the calculation of the statistics on this message ended.                  |
| 7    | BuyOrderCount          |           | required | Number of buy orders received within the calculation window.                   |
| 8    | SellOrderCount         |           | required | Number of sell orders received within the calculation window.                  |
| 9    | BuyOrderSize           |           | required | Cumulative quantity of all buy orders received within the calculation window.  |
| 10   | SellOrderSize          |           | required | Cumulative quantity of all sell orders received within the calculation window. |
| 11   | BuyOrderCancellations  |           | required | Number of buy orders cancelled by clients within the calculation window.       |
| 12   | SellOrderCancellations |           | required | Number of sell orders cancelled by clients within the calculation window.      |
| 13   | BidAskSpread           |           | required | Most Recent Bid/Ask spread at the time of publication of the message.          |

## Components

### Component Instrument category MarketData (8)

#### Synopsis

The Instrument component block contains all the fields commonly used to describe a security or instrument.

|       Name       | Presence |                       Documentation                       |
|------------------|----------|-----------------------------------------------------------|
| SecurityID       | required | Identifies the security being traded.                     |
| SecurityIDSource | required | Identifies the class or source of the security e.g. ISIN. |

## Fields

|          Name          | Tag |          Type           |                                    Synopsis                                    |
|------------------------|-----|-------------------------|--------------------------------------------------------------------------------|
| BidAskSpread           | 1   | Price                   | Most Recent Bid/Ask spread at the time of publication of the message.          |
| BuyOrderCancellations  | 2   | Integer                 | Number of buy orders cancelled within the calculation window.                  |
| BuyOrderCount          | 3   | Integer                 | Number of buy orders received within the calculation window.                   |
| BuyOrderSize           | 4   | Quantity                | Cumulative quantity of all buy orders received within the calculation window.  |
| EndTime                | 5   | Timestamp               | Time the calculation of the statistics on this message ended.                  |
| ExecutedSize           | 6   | Quantity                | Size executed.                                                                 |
| ExecutionVenue         | 7   | ExecutionVenueCodeSet   | Venue from which market data is received for the instrument.                   |
| MessageType            | 9   | MessageTypeCodeSet      | Identifies the type of message e.g. AddOrder.                                  |
| NewPrice               | 10  | Price                   | New price of the order.                                                        |
| NewQuantity            | 11  | Quantity                | New displayed quantity of the order.                                           |
| NumberOfTrades         | 12  | Integer                 | Count of all trades for the day.                                               |
| OrderBookType          | 13  | OrderBookTypeCodeSet    | The type of order book.                                                        |
| OrderID                | 14  | String                  | Unique identifier of the order.                                                |
| OrderType              | 15  | OrderTypeCodeSet        | The type of order placed e.g. Limit order.                                     |
| Participant            | 16  | String                  | Identity of trading participant that submitted the order.                      |
| PreviousPrice          | 17  | Price                   | Previous price of the order.                                                   |
| PreviousQuantity       | 18  | Quantity                | Previous displayed quantity of the order.                                      |
| Price                  | 19  | Price                   | Executed price.                                                                |
| SecurityID             | 20  | String                  | Identifies the security being traded.                                          |
| SecurityIDSource       | 21  | SecurityIDSourceCodeSet | Identifies the class or source of the security being traded.                   |
| SellOrderCancellations | 22  | Integer                 | Number of sell orders cancelled by clients within the calculation window.      |
| SellOrderCount         | 23  | Integer                 | Number of sell orders received within the calculation window.                  |
| SellOrderSize          | 24  | Quantity                | Cumulative quantity of all sell orders received within the calculation window. |
| Side                   | 25  | SideCodeSet             | The side of the order.                                                         |
| Size                   | 26  | Quantity                | Displayed size of the order.                                                   |
| StartTime              | 27  | Timestamp               | Time the calculation of the statistics on this message began.                  |
| Timestamp              | 28  | Timestamp               | Time the message was generated                                                 |
| TradeID                | 29  | String                  | Unique identifier of the trade.                                                |
| TradeType              | 30  | TradeTypeCodeSet        | The type of trade that was executed.                                           |
| TransactionTime        | 31  | Timestamp               | Execution timestamp as reported by the supported market.                       |
| Volume                 | 32  | Quantity                | Cumulative volume of all trades for the trading day.                           |
| VWAP                   | 33  | Price                   | Volume weighted average price for the day for all trades.                      |

## Codesets

### Codeset ExecutionVenueCodeSet type Integer (7)

#### Synopsis

|        Name         | Value |  Id  | Sort |       Synopsis        |
|---------------------|-------|------|------|-----------------------|
| LondonStockExchange | 1     | 7001 | 1    | London Stock Exchange |
| Turquoise           | 2     | 7002 | 2    | Turquoise Exchange    |
| TRADEcho            | 3     | 7003 | 3    | TRADEcho              |

### Codeset MessageTypeCodeSet type String (9)

#### Synopsis

|    Name     | Value |  Id  | Sort |                                                 Synopsis                                                 |
|-------------|-------|------|------|----------------------------------------------------------------------------------------------------------|
| AddOrder    | A     | 9001 | 1    | Indicates the first order of a given side of an MBO snapshot.                                            |
| DeleteOrder | D     | 9002 | 2    | Sent to instruct recipients to delete an order from the retrospective order book.                        |
| ModifyOrder | M     | 9003 | 3    | Sent to instruct recipients to update an order’s price and/or size on the retrospective order book.      |
| Trade       | T     | 9004 | 4    | Sent to indicate trades executed on supported markets.                                                   |
| Statistics  | S     | 9005 | 5    | Contains a set of statistics that are updated frequently, usually as a result of executions.             |
| Analytics   | a     | 9006 | 6    | Analytics Message is used to disseminate additional statistics including order book activity statistics. |

### Codeset OrderBookTypeCodeSet type String (13)

#### Synopsis

|    Name    | Value |  Id   | Sort |  Synopsis  |
|------------|-------|-------|------|------------|
| AllBooks   | 0     | 13001 | 1    | All books  |
| FirmQuote  | 1     | 13002 | 2    | Firm quote |
| OffBook    | 2     | 13003 | 3    | Off-book   |
| Electronic | 3     | 13004 | 4    | Electronic |

### Codeset OrderTypeCodeSet type Integer (126)

#### Synopsis

|  Name  | Value |   Id   | Sort |   Synopsis   |
|--------|-------|--------|------|--------------|
| Limit  | 0     | 126001 | 1    | Limit order  |
| Market | 1     | 126002 | 2    | Market order |

### Codeset SecurityIDSourceCodeSet type String (21)

#### Synopsis

Identifies class or source of the security.

|   Name   | Value |  Id   | Sort |                                                                   Synopsis                                                                   | Elaboration |
|----------|-------|-------|------|----------------------------------------------------------------------------------------------------------------------------------------------|-------------|
| ISIN     | 1     | 21001 | 1    | International Securities Identification Number (ISIN) conforming to [ISO 6166](https://www.iso.org/standard/78502.html).                     |             |
| RIC      | 2     | 21002 | 2    | Refinitiv Instrument Code (previously Reuters Instrument Code), or RIC, is a ticker code used to identify financial instruments and indices. |             |
| Exchange | 3     | 21003 | 3    | Exchange symbol                                                                                                                              |             |

### Codeset SideCodeSet type String (25)

#### Synopsis

| Name | Value |  Id   | Sort |  Synopsis  |
|------|-------|-------|------|------------|
| Buy  | B     | 25001 | 1    | Buy order  |
| Sell | S     | 25002 | 2    | Sell order |

### Codeset TradeTypeCodeSet type Integer (30)

#### Synopsis

|           Name           | Value |  Id   | Sort |           Synopsis            |
|--------------------------|-------|-------|------|-------------------------------|
| RegularOrContinuousTrade | 0     | 30001 | 1    | Regular (or Continuous) Trade |
| AuctionTradeBulk         | 1     | 30002 | 2    | Auction Trade – Bulk          |
| AuctionTradeIndividual   | 2     | 30003 | 3    | Auction Trade – Individual    |
| Reserve                  | 3     | 30004 | 4    | Reserve                       |
| TradeCancellation        | 4     | 30005 | 5    | Trade Cancellation            |
| TradeCorrection          | 5     | 30006 | 6    | Trade Correction              |

## Datatypes

|   Name    |                                                            Synopsis                                                            |  Base   |            Example             |
|-----------|--------------------------------------------------------------------------------------------------------------------------------|---------|--------------------------------|
| Timestamp | An instant in time. SHOULD be encoded as an ISO 8601-encoded ASCII string with a zone offset of 'Z'.                           |         | 2023-11-13T16:37:09.974402382Z |
| String    | A Unicode string. SHOULD be encoded using UTF-8.                                                                               |         | Hello, world!                  |
| Double    | A double-precision floating-point value compatible with IEEE 754.                                                              |         | 123.456                        |
| Price     | A float field representing a price. Note the number of decimal places may vary.                                                | Double  | 123.456                        |
| Integer   | Value must be positive and may not contain leading zeros.                                                                      |         | 123                            |
| Quantity  | An integer field capable of storing a whole number (no decimal places) of "shares" (for securities denominated in whole units) | Integer | 123                            |

