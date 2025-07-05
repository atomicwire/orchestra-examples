import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
  id("application")
  id("io.atomicwire.gradle.orchestra")
  id("org.openapi.generator")
  id("org.springframework.boot")
}

dependencies {

  // Spring Boot BOM
  implementation(platform(SpringBootPlugin.BOM_COORDINATES))
  annotationProcessor(platform(SpringBootPlugin.BOM_COORDINATES))

  implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
  implementation(group = "org.springframework.boot", name = "spring-boot-starter-validation")
}

orchestra {
  specification {
    markdown {
      reference(orchestraHub(name = "fix-latest", version = "ep295"))
      enableSpotless()
    }

    // Specify the corresponding JSON datatype for each datatype in the Orchestra specification.
    encoding {
      datatypeMapping(
          mapOf(
              "int" to mapOf("JSON" to "number"),
              "String" to mapOf("JSON" to "string"),
              "Pattern" to mapOf("JSON" to "string")))
    }
  }

  // Generate a JSON Schema for inclusion in the OpenAPI specification
  jsonSchema { namespace = "org.example.orchestra" }
}

// Colocate the two files so that the provided openapi.yaml can easily reference Schemas from the
// generated schemas.json file
val colocateOpenApiFiles =
    tasks.register<Copy>("colocateOpenApiFiles") {
      from(tasks.named("orchestraGenerateJsonSchema")) { rename { "schemas.json" } }
      from("openapi.yaml")

      into(layout.buildDirectory.dir("tmp/openapi"))
    }

val generatedSourceDir = layout.buildDirectory.dir("generated/sources/openapi-springboot")

// Configure the OpenAPI Generator
val openApiGenerate =
    tasks.named<GenerateTask>("openApiGenerate") {
      inputs.dir(colocateOpenApiFiles.map { it.destinationDir })
      inputSpec.set(colocateOpenApiFiles.map { "${it.destinationDir}/openapi.yaml" })

      generatorName.set("spring")

      // configure the package name
      val basePackage = "org.example.orchestra.springboot"
      invokerPackage.set(basePackage)
      modelPackage.set("${basePackage}.model")
      apiPackage.set("${basePackage}.api")
      configOptions.put("configPackage", "${basePackage}.config")

      configOptions.putAll(
          mapOf(
              // generate Api Delegate classes to enable simple implementation by providing a spring
              // Bean
              "delegatePattern" to "true",
              "useSpringBoot3" to "true"))

      // skip generating some extraneous things
      generateApiDocumentation.set(false)
      openapiGeneratorIgnoreList.set(listOf("pom.xml", "src/test/"))
      configOptions.putAll(mapOf("documentationProvider" to "none", "openApiNullable" to "false"))

      outputDir.set(generatedSourceDir.get().toString())
    }

sourceSets { main { java { srcDir(openApiGenerate) } } }

// Exclude generated code from spotless
spotless {
  java {
    targetExclude(
        "${generatedSourceDir.get().asFile.relativeTo(layout.projectDirectory.asFile)}/**")
  }
}
