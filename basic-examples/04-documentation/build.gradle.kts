plugins {
  id("io.atomicwire.gradle.orchestra")
  id("com.bmuschko.docker-remote-api")
}

orchestra {
  specification { markdown { reference(orchestraHub(name = "fix-4.4", version = "4.4")) } }

  documentation {
    // Enable documentation
    messageDefinitionReport {}
  }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraGenerateMessageDefinitionReportPdf"))

  doLast {
    println("Created Message Definition Report in ${orchestra.documentation.buildDir.get()}")
  }
}
