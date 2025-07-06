# Java application

This example demonstrates how to build a basic Java application that processes and manipulates data using an Orchestra specification (FIX 4.4).

The example application:
* Loads input data from the designated [example-input-data](./example-input-data) directory.
* Parses FIX tagvalue-encoded data using QuickFIX (through an existing QuickFIX Resource Accessor).
* Calculates the order count and total order quantity by instrument for all `NewOrderSingle` messages.
* Outputs a summary of the computed statistics.

## Configuration

The [build.gradle.kts](./build.gradle.kts) includes the Orchestra plugin with configuration to use a reference standard from [Orchestra Hub](https://orchestrahub.org/) and enable Java code generation.

```kotlin
plugins {
  id("io.atomicwire.gradle.orchestra")
}

orchestra {
  specification {
    name = "fix-4.4"
    repository(orchestraHub(group = "atomicwire", name = "fix-4.4-enriched", version = "4.4"))
  }

  // Enable Java code generation
  java {
    codeGen {
      packageName = "org.example.orchestra.fix44"
    }
  }
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :app-examples:01-basic:run
```

## Results

The summary statistics will be displayed in the console.
