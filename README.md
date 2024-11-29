# Orchestra Gradle Plugin

> **Warning**: The examples provided in this repository are experimental.

> **Notice**: Although these examples are [open source](LICENSE), the libraries obtained from the Atomic Wire Maven repository require a license. To request an evaluation license, please contact us via [our website](https://www.atomicwire.io/).

<!-- TOC -->
* [Introduction](#introduction)
  * [Basic examples](#basic-examples)
  * [Application examples](#application-examples)
* [Plugin configuration](#plugin-configuration)
<!-- TOC -->

## Introduction

[Orchestra](https://www.fixtrading.org/standards/fix-orchestra-online/) is an open industry standard created by the [FIX Trading Community](https://www.fixtrading.org) to establish **machine-readable rules of engagement** between counterparties.

This plugin offers convenient tools for working with Orchestra specifications. Users can create and validate specs, manage versioning, and verify compatibility for particular encodings. It also allows for the generation of various useful artifacts, such as code libraries, schemas, and documentation. 

The plugin also enables users to integrate Orchestra into their build pipelines.

### Basic examples

The [basic examples](./basic-examples) show you how to configure the plugin for specific tasks. 

Examples should be run from the root directory of this repository using the Gradle wrapper (full instructions are in the `README.md` file in each subproject).


| Example                                               | Description                                                                         |
|-------------------------------------------------------|-------------------------------------------------------------------------------------|
| [01-orchestra-hub](./basic-examples/01-orchestra-hub) | Pull an existing spec from the [Orchestra Hub](https://orchestrahub.org) repository |
| [02-markdown](./basic-examples/02-markdown)           | Create a new Orchestra spec using Markdown                                          |
| [03-derived](./basic-examples/03-derived)             | Create a new Orchestra spec by customising an existing spec                         |
| [04-documentation](./basic-examples/04-documentation) | Generate a Message Definition Report (MDR) from an Orchestra spec                   |
| [05-avro-schema](./basic-examples/05-avro-schema)     | Generate Avro schemas from an Orchestra spec                                        |
| [06-json-schema](./basic-examples/06-json-schema)     | Generate JSON schemas from an Orchestra spec                                        |
| [07-java](./basic-examples/07-java)                   | Generate Java code from an Orchestra spec                                           |
| [08-quickfix](./basic-examples/08-quickfix)           | Generate a QuickFIX Data Dictionary from an Orchestra spec                          |
| [09-openapi](./basic-examples/09-openapi)             | Create a REST service using OpenAPI and Orchestra                                   |
| [10-verification](./basic-examples/10-verification)   | Check the style, validity and encoding compatibility of an Orchestra spec           |

Each basic example includes a Gradle task named `runExample`, configured to invoke the specific Orchestra plugin task that the example is demonstrating. For instance, to run [01-orchestra-hub](./basic-examples/01-orchestra-hub) using the Gradle wrapper, use the following command:

```shell
$ ./gradlew :basic-examples:01-orchestra-hub:runExample
```

Results are output to the subproject Gradle build folder (i.e. `build/orchestra`). For instance, the results for the [01-orchestra-hub](./basic-examples/01-orchestra-hub) example are output to `./basic-examples/01-orchestra-hub/build/orchestra/`


### Application examples

The [application examples](./app-examples) build on the [basic-examples](./basic-examples) to create runnable applications for various use-cases.


| Example                                                       | Description                                                                                                          |
|---------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| [01-basic](./app-examples/01-basic)                           | A basic Java application that uses the Orchestra Java Library to compute aggregations over FIX TagValue encoded data |
| [02-quickfix](./app-examples/02-quickfix)                     | Example QuickFIX/J server and client applications that use a QuickFIX Data Dictionary generated from Orchestra       |
| [03-openapi-springboot](./app-examples/03-openapi-springboot) | An OpenAPI Spring Boot application powered by Orchestra                                                              |

The application examples are each demonstrated differently. Please see their respective READMEs for the command to run them.

## Plugin configuration

The [settings.gradle](./settings.gradle) file contains the complete setup to run the examples. The settings relevant to the Orchestra plugin are:

```groovy
pluginManagement {
  plugins {
    id 'io.atomicwire.gradle.orchestra' version '<version>'
  }

  repositories {
    gradlePluginPortal()

    maven {
      name = 'atomicwire'
      url = uri('https://maven.atomicwire.dev/external')
    }
  }
}

```

To use the plugin in a subproject add the following to `build.gradle`:

```groovy
plugins {
  id 'io.atomicwire.gradle.orchestra'
}
```
