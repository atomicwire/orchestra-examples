# Orchestra Examples

> **Warning**: The examples provided in this repository are experimental.

<!-- TOC -->
* [Orchestra Examples](#orchestra-examples)
  * [Introduction](#introduction)
    * [Basic examples](#basic-examples)
    * [Application examples](#application-examples)
  * [Orchestra Plugin](#orchestra-plugin)
<!-- TOC -->

## Introduction

The Orchestra Examples are split into two distinct sections

### Basic examples

The [basic examples](./basic-examples) are targeted to highlight specific functionality offered by the Orchestra plugin.

### Application examples

The [application examples](./app-examples) build on the basic examples to create runnable applications showcasing
various use-cases.


## Orchestra Plugin

All examples utilise the Orchestra Plugin.

[settings.gradle](./settings.gradle) contains the complete setup to run these examples, however the Orchestra Gradle
Plugin settings of interest are:

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

Then to use the plugin in a subproject add the following to `build.gradle`:

```
plugins {
  id 'io.atomicwire.gradle.orchestra'
}
```
