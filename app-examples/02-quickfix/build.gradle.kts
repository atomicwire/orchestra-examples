plugins {
  id("application")
  id("io.atomicwire.gradle.orchestra")
}

configurations {
  create("quickfixCodeGenClasspath") {
    isCanBeConsumed = false
    isCanBeResolved = true
  }
}

dependencies {

  // QuickFIX
  implementation(libs.quickfixj)
  "quickfixCodeGenClasspath"(libs.orch.gen.quickfix.code.generator)

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
  specification { markdown { reference(orchestraHub(name = "fix-4.4", version = "4.4")) } }

  // Enable QuickFIX data dictionary generation
  quickfix { dataDictionary {} }
}

tasks.register<JavaExec>("runServer") {
  group = "application"
  dependsOn("orchestraGenerateQuickfixDataDictionary")

  val quickFixDataDictionary =
      tasks.named("orchestraGenerateQuickfixDataDictionary").get().outputs.files.singleFile

  classpath = sourceSets.main.get().runtimeClasspath
  mainClass = "org.example.orchestra.QuickFixEngineDataDictionaryApp"
  args(quickFixDataDictionary)
}

tasks.register<JavaExec>("runClient") {
  group = "application"
  dependsOn("orchestraGenerateQuickfixDataDictionary")

  val quickFixDataDictionary =
      tasks.named("orchestraGenerateQuickfixDataDictionary").get().outputs.files.singleFile

  classpath = sourceSets.main.get().runtimeClasspath
  mainClass = "org.example.orchestra.QuickFixClientDataDictionaryApp"
  args(quickFixDataDictionary)
}

val quickfixGeneratedSourcesDir = layout.buildDirectory.dir("generated/sources/quickfix")

val generateQuickFixMessages =
    tasks.register<JavaExec>("generateQuickFixMessages") {
      group = "quickfix"
      description = "Generate QuickFIX/J message sources for the customized data dictionary"

      // Register the project's Orchestra spec as an input to this task
      val orchestraSpec = orchestra.specification.provider
      inputs.file(orchestraSpec)

      // Register the generated sources build directory as an output of this task
      outputs.dir(quickfixGeneratedSourcesDir)

      classpath = configurations["quickfixCodeGenClasspath"]
      mainClass = "org.quickfixj.orchestra.CodeGeneratorJ"

      args(
          "--orchestra-file",
          orchestraSpec.get().absolutePath,
          "--output-dir",
          quickfixGeneratedSourcesDir.get().asFile.absolutePath,

          // Disable FIXT package generation to instead utilize the version of FIXT11.xml bundled
          // with QuickFIX/J
          "--no-generateFixt11Package",

          // Use the same version specific package names as QuickFIX/J
          "--fix-version",
          "FIX44")
    }

sourceSets { main { java { srcDir(generateQuickFixMessages) } } }

// Exclude generated code from spotless
spotless {
  java {
    targetExclude(
        "${quickfixGeneratedSourcesDir.get().asFile.relativeTo(layout.projectDirectory.asFile)}/**")
  }
}
