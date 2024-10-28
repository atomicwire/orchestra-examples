# QuickFIX application example

An QuickFIX based example application that utilises the custom QuickFIX data dictionary generated in
[basic-examples/08-quickfix](../../basic-examples/08-quickfix)

Two applications are started, the first is the [QuickFIX engine](./src/main/java/org/example/orchestra/QuickFixEngineDataDictionaryApp.java)
running with the custom Data Dictionary, the second is the [QuickFIX client](./src/main/java/org/example/orchestra/QuickFixClientDataDictionaryApp.java)
application that will send a mixture of valid and invalid messages.

The client application will send the following messages:

* A message that conforms to the Data Dictionary
* An invalid message that is missing the required `Account` field
* An invalid message that contains a field `SecondaryOrderID` which isn't in the custom `NewOrderSingle` message
  definition

## Run

From the command line start the QuickFIX engine using

```
./gradlew :app-examples:02-quickfix:runQuickFixEngineApp
```

In another console window start the QuickFIX client app using

```
./gradlew :app-examples:02-quickfix:runQuickFixClientApp
```

## Results

Log messages will be output in both console windows showing the communication between QuickFIX engine and client.

The QuickFIX engine application will terminate after 2 minutes.
