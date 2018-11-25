import com.jfrog.bintray.gradle.BintrayExtension

dependencies {
    api("com.amazonaws:aws-java-sdk-dynamodb")
}

publishing {
    publications {
        val main by creating(MavenPublication::class) {
            from(components["java"])

            val sourcesJar by tasks
            val javadocJar by tasks

            artifact(sourcesJar)
            artifact(javadocJar)
        }
    }
}

bintray {
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

tasks {
    val sourcesJar by creating(Jar::class) {
        from(sourceSets["main"].allJava)
        classifier = "sources"
    }

    val javadocJar by creating(Jar::class) {
        val javadoc by tasks

        from(javadoc)
        classifier = "javadoc"
    }
}
