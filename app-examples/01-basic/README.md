# Basic example

A basic app that demonstrates parsing and using data according to an Orchestra spec (FIX 4.4).

The example application

- Reads input from files in the configured [example-input-data](./example-input-data) directory line by line.
- Parses the tag/value-encoded data using QuickFIX (via a QuickFix Resource Accessor).
- Sums the total order count and quantity of all `NewOrderSingle` messages
- Returns a summary of the statistics

## Run

To execute the example application run

```
./gradlew :app-examples:01-basic:run
```

## Results 

The Summary results will be displayed in the console.
