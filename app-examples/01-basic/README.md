# Java application

This example shows how to build a Java application that parses and manipulates data according to an Orchestra spec (FIX 4.4).

The example application:
* Reads input data files from the configured [example-input-data](./example-input-data) directory.
* Parses FIX TagValue encoded data using QuickFIX (via an existing QuickFix Resource Accessor).
* Sums the order count and total order quantity by instrument for all `NewOrderSingle` messages.
* Returns a summary of the computed statistics.

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :app-examples:01-basic:run
```

## Results

The summary results will be displayed in the console.
