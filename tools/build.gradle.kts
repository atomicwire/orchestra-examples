plugins {
  id("application")
  id("io.atomicwire.gradle.orchestra")
}

dependencies {

  // Orchestra Java library
  implementation(libs.orchestra.core)

  // Orchestra Java plugins for FIX 4.4
  implementation(libs.orchestra.fix.plugins)

  // Picocli
  implementation(libs.picocli)

  // Jakarta Annotation API
  implementation(libs.jakarta.annotation.api)

  // Lombok
  annotationProcessor(libs.lombok)
  compileOnly(libs.lombok)
  testAnnotationProcessor(libs.lombok)
  testCompileOnly(libs.lombok)

  // Logging
  implementation(libs.slf4j.api)
  runtimeOnly(libs.log4j.slf4j2.impl)
}

orchestra {
  specification {
    name = "fix-4.4"
    repository(orchestraHub(group = "atomicwire", name = "fix-4.4-enriched", version = "4.4"))
  }

  java {
    packaging { enabled = false }

    codeGen { packageName = "org.example.orchestra.fix44" }
  }
}

spotless {
  java {
    // Exclude generated Java sources from style enforcement
    targetExclude(
        "${orchestra.java.codeGen.outputDir.get().asFile.relativeTo(layout.projectDirectory.asFile)}/**")
  }
}

tasks.register<JavaExec>("generateBasicAppExampleData") {
  val outputFile = layout.buildDirectory.file("generated-data.txt")
  outputs.file(outputFile)

  classpath = sourceSets.main.get().runtimeClasspath
  mainClass = "org.example.orchestra.DataGeneratorCli"
  args("--message-count", "10000", "--output-file", outputFile.get())
}
