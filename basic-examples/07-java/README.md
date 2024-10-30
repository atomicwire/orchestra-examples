# Java example

This example shows how to generate code for use with the Orchestra Java library.

Java will be generated for the messages, components, fields and codesets defined in the Orchestra spec.

## Configuration

To generate Java code from an Orchestra specification, additional type information is needed to map each datatype in the Orchestra spec to the corresponding Java datatype.

Datatype mapping is supplied via the `encoding` extension in the [build.gradle](./build.gradle).

The Java code generation is activated by the presence of the Java `codeGen` extension. A `packageName` value must be provided.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}

    encoding {
      datatypeMapping([
        double: [
          Java: 'double',
        ],
        string: [
          Java: 'string',
        ],
        timestamp: [
          Java: 'instant',
        ],
      ])
    }
  }

  java {
    codeGen {
      packageName = 'org.example.orchestra'
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
