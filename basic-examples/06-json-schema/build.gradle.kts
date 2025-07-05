plugins { id("io.atomicwire.gradle.orchestra") }

orchestra {
  specification {
    markdown {}

    // Specify the corresponding JSON datatype for each datatype in the Orchestra specification.
    encoding {
      datatypeMapping(
          mapOf(
              "Double" to mapOf("JSON" to "number"),
              "Integer" to mapOf("JSON" to "number"),
              "Price" to mapOf("JSON" to "number"),
              "Quantity" to mapOf("JSON" to "number"),
              "String" to mapOf("JSON" to "string"),
              "Timestamp" to mapOf("JSON" to "string")))
    }
  }

  // Enable JSON schema generation
  jsonSchema { namespace = "org.example.orchestra" }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraGenerateJsonSchema"))

  doLast { println("Generated JSON schema in ${orchestra.jsonSchema.outputDir.get()}") }
}
