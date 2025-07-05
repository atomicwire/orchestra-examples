plugins { id("io.atomicwire.gradle.orchestra") }

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
  java { codeGen { packageName = "org.example.orchestra" } }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraGenerateJava"))

  doLast { println("Generated Orchestra Java code in ${orchestra.java.codeGen.outputDir.get()}") }
}
