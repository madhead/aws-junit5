import java.util.Properties

plugins {
    id("org.asciidoctor.jvm.convert").version("3.3.2")
    id("io.github.gradle-nexus.publish-plugin").version("1.1.0")
}

repositories {
    mavenCentral()
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("OSSRH_USER") ?: return@sonatype)
            password.set(System.getenv("OSSRH_PASSWORD") ?: return@sonatype)
        }
    }
}

configure(
    subprojects
        - project(":dynamo")
        - project(":s3")
        - project(":kinesis")
        - project(":sns")
        - project(":sqs")
        - project(":ses")
) {
    apply<JavaLibraryPlugin>()
    apply<JacocoPlugin>()
    apply<SigningPlugin>()
    apply<MavenPublishPlugin>()

    repositories {
        mavenCentral()
    }

    dependencies {
        val api by configurations
        val testImplementation by configurations
        val testRuntimeOnly by configurations

        api(platform("org.junit:junit-bom:5.7.1"))
        api("org.junit.jupiter:junit-jupiter-api")

        testImplementation(platform("org.junit:junit-bom:5.7.1"))
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-params")

        testRuntimeOnly(platform("org.junit:junit-bom:5.7.1"))
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    configure<JavaPluginExtension> {
        withJavadocJar()
        withSourcesJar()
    }

    configure<SigningExtension> {
        val key = System.getenv("SIGNING_KEY") ?: return@configure
        val password = System.getenv("SIGNING_PASSWORD") ?: return@configure
        val publishing: PublishingExtension by project

        useInMemoryPgpKeys(key, password)
        sign(publishing.publications)
    }

    configure<PublishingExtension> {
        publications {
            val main by creating(MavenPublication::class) {
                from(components["java"])

                pom {
                    name.set("aws-junit5 :: ${project.name}")
                    description.set("aws-junit5 :: ${project.name}")
                    url.set("https://github.com/madhead/aws-junit5")
                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("madhead")
                            name.set("Siarhei Krukau")
                            email.set("siarhei.krukau@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git@github.com:madhead/aws-junit5.git")
                        url.set("https://github.com/madhead/aws-junit5")
                    }
                }
            }
        }
    }

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.7"
    }

    tasks {
        withType<Test> {
            systemProperties = Properties().apply {
                load(File(rootDir, ".github/workflows/test.sys").bufferedReader())
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
    register<Javadoc>("javadocs") {
        group = "Documentation"
        destinationDir = file("$buildDir/docs/javadoc")
        title = project.name
        with(options as StandardJavadocDocletOptions) {
            links = listOf(
                "https://docs.oracle.com/javase/8/docs/api/",
                "https://junit.org/junit5/docs/current/api/",
                "https://sdk.amazonaws.com/java/api/latest/",
                "https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/"
            )
        }
        subprojects.forEach {
            it.tasks.withType<Javadoc>().forEach {
                source += it.source
                classpath += it.classpath
                includes += it.includes
                excludes += it.excludes
            }
        }
    }

    asciidoctor {
        setSourceDir(file("docs"))
        sources {
            include("index.adoc")
        }
        setOutputDir(file("$buildDir/docs/asciidoc"))
        setBaseDir(file("docs"))
    }
}
