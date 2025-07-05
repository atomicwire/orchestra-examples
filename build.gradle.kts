import com.diffplug.gradle.spotless.SpotlessExtension

plugins { id("com.diffplug.spotless") apply false }

allprojects {
  apply(plugin = "com.diffplug.spotless")

  repositories {
    mavenCentral()

    maven {
      name = "atomicwire"
      url = uri("https://maven.atomicwire.dev/external")
    }
  }

  pluginManager.withPlugin("java") {
    configure<JavaPluginExtension> { toolchain { languageVersion = JavaLanguageVersion.of(17) } }

    configure<SpotlessExtension> {
      java {
        licenseHeaderFile("${rootDir}/.license-header-java.txt")
        googleJavaFormat("1.22.0")
        toggleOffOn()
      }
    }
  }

  configure<SpotlessExtension> {
    kotlinGradle {
      ktfmt("0.55")
      toggleOffOn()
    }
  }
}

tasks.wrapper {
  gradleVersion = "8.12.1"
  distributionType = Wrapper.DistributionType.ALL
}
