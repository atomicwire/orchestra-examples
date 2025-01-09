# Orchestra Gradle Plugin

<!-- TOC -->
* [Introduction](#introduction)
* [Examples](#examples)
  * [Plugin features](#plugin-features)
  * [Applications](#applications)
* [Plugin configuration](#plugin-configuration)
<!-- TOC -->

## Introduction

[Orchestra](https://www.fixtrading.org/standards/fix-orchestra-online/) is an open industry standard created by the [FIX Trading Community](https://www.fixtrading.org) to establish **machine-readable rules of engagement** between counterparties.

This plugin provides useful features for working with Orchestra specifications:
* Create and validate specifications
* Manage specification versions
* Verify compatibility with specific data encodings
* Generate build artifacts (e.g., code libraries, schemas, documentation) to facilitate application development
* Integrate Orchestra into CI/CD pipelines
* Use specifications published by other counterparties

The motivation behind developing tools for the Orchestra standard includes:
* Simplifying the adoption of Orchestra
* Reducing implementation effort for industry participants
* Streamlining the development process for integrating Orchestra specifications into applications

## Examples 

### Plugin features

The [basic examples](./basic-examples) demonstrate how to configure core plugin features, including building a specification from Markdown, generating documentation, and creating build artifacts for application integration.

Examples should be run from the root directory of this repository using the Gradle wrapper (full instructions are included in the `README.md` file of each subproject).

| Example                                               | Description                                                                                                                    |
|-------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| [01-orchestra-hub](./basic-examples/01-orchestra-hub) | Fetch an Orchestra specification from a central repository ([Orchestra Hub](https://orchestrahub.org))                         |
| [02-markdown](./basic-examples/02-markdown)           | Build a custom specification from a Markdown file                                                                              |
| [03-derived](./basic-examples/03-derived)             | Create a derived specification from a reference specification                                                                  |
| [04-documentation](./basic-examples/04-documentation) | Generate PDF and EPUB documentation from an Orchestra specification                                                            |
| [05-avro-schema](./basic-examples/05-avro-schema)     | Generate Avro schemas from an Orchestra specification                                                                          |
| [06-json-schema](./basic-examples/06-json-schema)     | Generate JSON schemas from an Orchestra specification                                                                          |
| [07-java](./basic-examples/07-java)                   | Generate Java code from an Orchestra specification                                                                             |
| [08-quickfix](./basic-examples/08-quickfix)           | Generate a QuickFIX Data Dictionary from an Orchestra specification                                                            |
| [09-verification](./basic-examples/09-verification)   | Verify that an Orchestra specification adheres to syntax rules, semantic correctness, and encoding compatibility requirements. |
| [10-scenarios](./basic-examples/10-scenarios)         | Create custom message definitions for specific contexts.                                                                       |

Each example includes a Gradle task named `runExample`, configured to invoke the specific Orchestra plugin task that the example is demonstrating. For instance, to run [01-orchestra-hub](./basic-examples/01-orchestra-hub) using the Gradle wrapper, use the following command:

```shell
$ ./gradlew :basic-examples:01-orchestra-hub:runExample
```

Results are output to the subproject Gradle build folder (i.e. `build/orchestra`). For instance, the results for the [01-orchestra-hub](./basic-examples/01-orchestra-hub) example are output to `./basic-examples/01-orchestra-hub/build/orchestra/`

### Applications

The [application examples](./app-examples) build on the [basic-examples](./basic-examples) to create runnable applications for various use-cases.

| Example                                                       | Description                                                                                                          |
|---------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| [01-basic](./app-examples/01-basic)                           | A basic Java application that uses the Orchestra Java Library to compute aggregations over FIX tagvalue encoded data |
| [02-quickfix](./app-examples/02-quickfix)                     | Example QuickFIX/J server and client applications that use a QuickFIX Data Dictionary generated from Orchestra       |
| [03-openapi-springboot](./app-examples/03-openapi-springboot) | An OpenAPI Spring Boot application powered by Orchestra                                                              |

The application examples are each demonstrated differently. Please refer to their respective `README.md` files for the commands to run them.

## Plugin configuration

The [settings.gradle](./settings.gradle) file contains the complete setup to run the examples. The settings relevant to the Orchestra plugin are:

```groovy
pluginManagement {
  plugins {
    id 'io.atomicwire.gradle.orchestra' version '<version>'
  }

  repositories {
    gradlePluginPortal()

    // Configure access to Atomic Wire maven repository
    maven {
      name = 'atomicwire'
      url = uri('https://maven.atomicwire.dev/external')
    }
  }
}

```

To use the plugin in a subproject add the following to `build.gradle` file:

```groovy
plugins {
  id 'io.atomicwire.gradle.orchestra'
}
```
