plugins { id("io.atomicwire.gradle.orchestra") }

orchestra {
  specification {
    markdown {
      // Specify the name and version of the reference specification
      reference(orchestraHub(name = "fix-4.4", version = "4.4"))
    }
  }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraBuildSpec"))

  doLast { println("Created Orchestra spec ${orchestra.specification.provider.get()}") }
}
