import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import java.util.Properties

plugins {
    id("com.gradle.build-scan").version("2.3")
    id("io.spring.dependency-management").version("1.0.8.RELEASE").apply(false)
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

configure(
    subprojects
        - project("dynamodb")
) {
    apply<JavaLibraryPlugin>()
    apply<DependencyManagementPlugin>()
    apply<JacocoPlugin>()

    repositories {
        jcenter()
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.junit:junit-bom:5.5.1")

            // By default, the code is compiled against the lowest supported AWS SDK versions.
            // If BY_DEV_MADHEAD_AWS_JUNIT5_USE_LATEST_AWS_SDK environment variable is set to true we want to test the
            // code against the latest available AWS SDK versions.
            val v1: String
            val v2: String

            if (System.getenv("BY_DEV_MADHEAD_AWS_JUNIT5_USE_LATEST_AWS_SDK") == "true") {
                v1 = "1.+"
                v2 = "2.+"
            } else {
                v1 = "1.11.79"
                v2 = "2.0.0"
            }
            mavenBom("com.amazonaws:aws-java-sdk-bom:$v1")
            mavenBom("software.amazon.awssdk:bom:$v2")
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
        toolVersion = "0.8.4"
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
