import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import java.util.Properties

plugins {
    id("io.spring.dependency-management").version("1.0.6.RELEASE").apply(false)
}

subprojects {
    apply<JavaLibraryPlugin>()
    apply<DependencyManagementPlugin>()
    apply<JacocoPlugin>()

    repositories {
        jcenter()
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.junit:junit-bom:5.2.0")
            mavenBom("com.amazonaws:aws-java-sdk-bom:1.11.79")
            mavenBom("software.amazon.awssdk:bom:2.0.0")
        }
    }

    dependencies {
        val api by configurations
        val testImplementation by configurations
        val testRuntimeOnly by configurations

        api("org.junit.jupiter:junit-jupiter-api")

        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.2"
    }

    tasks {
        withType<Test> {
            systemProperties = Properties().apply {
                load(File(rootDir, "gitlab/test.sys").bufferedReader())
            }.mapKeys { entry -> entry.key.toString() }
            useJUnitPlatform()
            testLogging {
                showStandardStreams = true
            }
        }

        withType<JacocoReport> {
            reports {
                xml.isEnabled = true
                html.isEnabled = true
            }
        }
    }
}

tasks {
    wrapper {
        gradleVersion = "5.2.1"
        distributionType = Wrapper.DistributionType.ALL
    }
}
