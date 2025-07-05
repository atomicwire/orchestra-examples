plugins {
  id("io.atomicwire.gradle.orchestra")
  id("com.diffplug.spotless")
}

orchestra {
  specification {
    // By default, the plugin will search for a Markdown file at
    // `orchestra/specification/<project-name>.md`
    markdown {
      // Enable Spotless to automatically format Orchestra Markdown and enforce style
      enableSpotless()
    }
  }
}

tasks.named("runExample") {
  dependsOn(tasks.named("orchestraBuildSpec"))

  doLast { println("Created Orchestra spec ${orchestra.specification.provider.get()}") }
}
