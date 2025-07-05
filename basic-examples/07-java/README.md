# Java Code Generation

This example demonstrates how to generate Java code directly from an Orchestra specification.

This feature allows developers to streamline the creation of applications that process Orchestra-specified data using the Orchestra Java library with built-in support for multiple data encodings. 

The plugin generates Java classes for the messages, components, fields, and codesets specified in the Orchestra specification.

The example uses the Order Book specification from a [previous](../02-markdown) example.

## Configuration

To generate Java code from an Orchestra specification, additional type information is needed to map each datatype in the Orchestra specification to the corresponding Java datatype.

Add the `encoding` extension to the [build.gradle.kts](./build.gradle.kts) file and define a datatype mapping for each datatype in your Orchestra specification.

Java code generation is enabled by the presence of the Java `codeGen` extension. A `packageName` value must be provided.

```kotlin
orchestra {
  specification {
    markdown {}

    // Specify the corresponding Java type for each datatype in the Orchestra specification.
    encoding {
      datatypeMapping(
          mapOf(
              "Double" to mapOf("Java" to "double"),
              "Integer" to mapOf("Java" to "int"),
              "Price" to mapOf("Java" to "double"),
              "Quantity" to mapOf("Java" to "int"),
              "String" to mapOf("Java" to "string"),
              "Timestamp" to mapOf("Java" to "instant")))
    }
  }

  // Enable Java code generation
  java {
    codeGen {
      packageName = "org.example.orchestra"
    }
  }
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:07-java:runExample
```
> **Note**: `runExample` is wired to call the `orchestraGenerateJava` task from the Orchestra plugin.

## Results

The Java source code will be output to the Gradle build folder.

```shell
$ ./basic-examples/07-java/build/generated/sources/orch-gen-java/main/java
```
