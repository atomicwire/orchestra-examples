# QuickFIX application

This example shows how to build a QuickFIX application using a QuickFIX Data Dictionary, and associated Java code, all generated from a custom Orchestra specification which is derived from FIX 4.4.

Two applications are started, the first is the [QuickFIX engine](./src/main/java/org/example/orchestra/QuickFixEngineDataDictionaryApp.java)
running with the custom Data Dictionary, the second is the [QuickFIX client](./src/main/java/org/example/orchestra/QuickFixClientDataDictionaryApp.java)
application that sends a mix of valid and invalid messages to the engine.

The client application sends the following messages:

* A valid message that conforms to the custom `NewOrderSingle` message in the QuickFIX Data Dictionary.
* An invalid message that is missing the required `Account` field.
* An invalid message that contains the field `SecondaryOrderID` which is not present in the custom `NewOrderSingle` message definition.

## Configuration

This example utilizes a custom Orchestra specification [02-quickfix.md](./orchestra/specification/02-quickfix.md).

## Run

Use the Gradle wrapper to start the QuickFIX engine.

```shell
$ ./gradlew :app-examples:02-quickfix:runServer
```

Then start the QuickFIX client application in a separate console window.

```shell
$ ./gradlew :app-examples:02-quickfix:runClient
```

## Results

Log messages will be output in both console windows showing the communication between the QuickFIX engine and client
application and the successful processing of a `NewOrderSingle` message

> **Note**: The QuickFIX engine will terminate after 2 minutes.

## Further information

### QuickFIX/J configuration

The engine and client applications each have a QuickFIX/J configuration file which can be found in [resources](./src/main/resources).

The QuickFIX Data Dictionary generated in this example application derives from FIX 4.4, and it utilises [FIXT](https://www.fixtrading.org/family-of-standards/fixt/)
for the session layer. Therefore, the QuickFIX/J configuration contains

    BeginString=FIXT.1.1
    DefaultApplVerID=FIX.4.4

For full details on the QuickFIX configuration options, see the [QuickFIX/J user manual](https://www.quickfixj.org/usermanual/2.3.0/usage/configuration.html)

### QuickFIX/J code generation

The example application contains a Gradle task that calls the QuickFIX/J code generator to generate the QuickFIX
messages source code for use in the Server and Client applications, the source is output to `build/generated/sources/quickfix`.

FIXT source-code generation is disabled to instead utilise the pre-built `FIXT11.xml` transport data dictionary available in QuickFIX/J.
