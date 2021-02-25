import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayPlugin
import java.util.Properties

plugins {
    id("com.jfrog.bintray").version("1.8.5").apply(false)
    id("org.asciidoctor.jvm.convert").version("3.3.2")
}

repositories {
    jcenter()
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
    apply<MavenPublishPlugin>()
    apply<BintrayPlugin>()

    repositories {
        jcenter()
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

    configure<PublishingExtension> {
        publications {
            val main by creating(MavenPublication::class) {
                from(components["java"])
            }
        }
    }

    configure<BintrayExtension> {
        user = System.getenv("BINTRAY_USER")
        key = System.getenv("BINTRAY_KEY")
        publish = true
        setPublications("main")
        pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
            repo = "maven"
            name = "${project.group}:${project.name}"
            setLicenses("MIT")
            vcsUrl = "https://gitlab.com/madhead/aws-junit5"
            version(delegateClosureOf<BintrayExtension.VersionConfig> {
                name = project.version.toString()
            })
        })
    }

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.6"
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
