plugins {
  id("io.atomicwire.gradle.orchestra")
  id("com.bmuschko.docker-remote-api")
}

orchestra {
  specification { markdown { reference(orchestraHub(name = "fix-latest", version = "ep295")) } }

  // Enable encoding compatibility checking for FIX tagvalue
  validation { fixCompatible {} }

  // Enable metadata checking for document generation
  documentation { messageDefinitionReport {} }
}

tasks.named("runExample") { dependsOn(tasks.named("orchestraValidateSpec")) }
