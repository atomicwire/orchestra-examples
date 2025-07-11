plugins { id("io.atomicwire.gradle.orchestra") }

orchestra {
  specification {
    // Specify the name and version of the specification to retrieve from the repository
    repository(orchestraHub(name = "fix-latest", version = "ep295"))
  }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraBuildSpec"))

  doLast { println("Downloaded Orchestra spec to ${orchestra.specification.provider.get()}") }
}
