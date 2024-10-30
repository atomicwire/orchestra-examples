# QuickFIX application

This example shows how to build a QuickFIX application using a QuickFIX Data Dictionary generated from a custom Orchestra specification in a [previous](../../basic-examples/08-quickfix) example.

Two applications are started, the first is the [QuickFIX engine](./src/main/java/org/example/orchestra/QuickFixEngineDataDictionaryApp.java)
running with the custom Data Dictionary, the second is the [QuickFIX client](./src/main/java/org/example/orchestra/QuickFixClientDataDictionaryApp.java)
application that sends a mix of valid and invalid messages.

The client application sends the following messages:

* A valid message that conforms to the generated QuickFIX Data Dictionary.
* An invalid message that is missing the required `Account` field.
* An invalid message that contains the field `SecondaryOrderID` which is not present in the custom `NewOrderSingle` message definition.

## Run

Use the Gradle wrapper to start the QuickFIX engine.

```shell
$ ./gradlew :app-examples:02-quickfix:runQuickFixEngineApp
```

Then start the QuickFIX client application Using a separate console window.

```shell
$ ./gradlew :app-examples:02-quickfix:runQuickFixClientApp
```

## Results

Log messages will be output in both console windows showing the communication between the QuickFIX engine and client application.

> **Note**: The QuickFIX engine will terminate after 2 minutes.
