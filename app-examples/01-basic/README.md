# Java application

This example shows how to build a Java application that parses and manipulates data according to an Orchestra spec (FIX 4.4).

The example application:
* Reads input data files from the configured [example-input-data](./example-input-data) directory.
* Parses FIX TagValue encoded data using QuickFIX (via an existing QuickFix Resource Accessor).
* Sums the order count and total order quantity by instrument for all `NewOrderSingle` messages.
* Returns a summary of the computed statistics.

## Configuration

The FIX 4.4 spec is fetched from Orchestra Hub and Java codegen configured in the [build.gradle](./build.gradle) file.

```groovy
orchestra {
  specification {
    name = 'fix-4.4'
    repository orchestraHub(group: 'atomicwire', name: 'fix-4.4-enriched', version: '4.4')
  }

  java {
    codeGen {
      packageName = 'org.example.orchestra.fix44'
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

The summary results will be displayed in the console.
