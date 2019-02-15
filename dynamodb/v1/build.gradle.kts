import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    `maven-publish`
    id("com.jfrog.bintray").version("1.8.4")
}

dependencies {
    api("com.amazonaws:aws-java-sdk-dynamodb")
}

publishing {
    publications {
        val main by creating(MavenPublication::class) {
            from(components["java"])

            val sourcesJar by tasks.creating(Jar::class) {
                from(sourceSets["main"].allJava)
                classifier = "sources"
            }

            val javadocJar by tasks.creating(Jar::class) {
                val javadoc by tasks

                from(javadoc)
                classifier = "javadoc"
            }

            artifact(sourcesJar)
            artifact(javadocJar)
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    setPublications("main")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "${project.group}:${project.name}"
        setLicenses("LGPL-3.0")
        vcsUrl = "https://gitlab.com/madhead/aws-junit5"
        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            name = project.version.toString()
        })
    })
}
