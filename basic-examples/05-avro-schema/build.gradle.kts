plugins { id("io.atomicwire.gradle.orchestra") }

orchestra {
  specification {
    markdown {}

    // Specify the corresponding Avro datatype for each datatype in the Orchestra specification.
    encoding {
      datatypeMapping(
          mapOf(
              "Double" to mapOf("Avro" to "double"),
              "Integer" to mapOf("Avro" to "int"),
              "Price" to mapOf("Avro" to "double"),
              "Quantity" to mapOf("Avro" to "int"),
              "String" to mapOf("Avro" to "string"),
              "Timestamp" to
                  mapOf(
                      "Avro" to
                          mapOf(
                              "standardType" to "long",
                              "avroLogicalType" to "timestamp-micros",
                          ))))
    }
  }

  // Enable Avro schema generation
  avro { namespace = "org.example.orchestra" }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraGenerateAvroSchema"))

  doLast { println("Generated Avro schemas in ${orchestra.avro.schemaOutputDir.get()}") }
}
