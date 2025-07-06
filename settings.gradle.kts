pluginManagement {
  plugins {
    id("com.bmuschko.docker-remote-api") version "9.4.0"
    id("com.diffplug.spotless") version "7.0.4"
    id("io.atomicwire.gradle.orchestra") version "0.1.18"
    id("org.openapi.generator") version "7.14.0"
    id("org.springframework.boot") version "3.5.3"
  }

  repositories {
    gradlePluginPortal()

    // Configure access to Atomic Wire maven repository
    maven {
      name = "atomicwire"
      url = uri("https://maven.atomicwire.dev/external")
    }
  }
}

rootProject.name = "orchestra-examples"

include(
    ":app-examples:01-basic",
    ":app-examples:02-quickfix",
    ":app-examples:03-openapi-springboot",
    ":basic-examples:01-orchestra-hub",
    ":basic-examples:02-markdown",
    ":basic-examples:03-derived",
    ":basic-examples:04-documentation",
    ":basic-examples:05-avro-schema",
    ":basic-examples:06-json-schema",
    ":basic-examples:07-java",
    ":basic-examples:08-quickfix",
    ":basic-examples:09-verification",
    ":basic-examples:10-scenarios",
    ":tools",
)
