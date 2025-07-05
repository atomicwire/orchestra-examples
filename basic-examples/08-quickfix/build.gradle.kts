plugins { id("io.atomicwire.gradle.orchestra") }

orchestra {
  specification { markdown { reference(orchestraHub(name = "fix-4.4", version = "4.4")) } }

  // Enable QuickFIX data dictionary generation
  quickfix { dataDictionary {} }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraGenerateQuickfixDataDictionary"))

  doLast {
    println(
        "Generated QuickFix Data Dictionary in ${orchestra.quickfix.dataDictionary.outputDir.get()}")
  }
}
