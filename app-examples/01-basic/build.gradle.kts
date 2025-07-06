plugins {
  id("application")
  id("io.atomicwire.gradle.orchestra")
}

dependencies {

  // Orchestra Java library
  implementation(libs.orchestra.core)
  implementation(libs.orchestra.accessor.quickfix)

  // Orchestra Java plugins for FIX 4.4
  implementation(platform(libs.orchestra.specs.bom))
  implementation(libs.orchestra.spec.fix44.plugins)

  // Jackson
  implementation(libs.jackson.databind)

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

  // Enable Java code generation
  java { codeGen { packageName = "org.example.orchestra.fix44" } }
}

spotless {
  java {
    // Exclude generated Java sources from style enforcement
    targetExclude(
        "${orchestra.java.codeGen.outputDir.get().asFile.relativeTo(layout.projectDirectory.asFile)}/**")
  }
}

application { mainClass = "org.example.orchestra.BasicApp" }

tasks.run.configure {
  val inputDir = layout.projectDirectory.dir("example-input-data")
  inputs.dir(inputDir)

  val outputFile = layout.buildDirectory.file("summary.json")
  outputs.file(outputFile)

  outputs.upToDateWhen {
    // Always run
    false
  }

  args(inputDir.asFile.toString(), outputFile.get().toString())
}
