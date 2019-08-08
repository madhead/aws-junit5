dependencies {
    api(project(":common"))
    api("com.amazonaws:aws-java-sdk-core")

    testImplementation("com.amazonaws:aws-java-sdk-dynamodb")
    testImplementation("com.amazonaws:aws-java-sdk-s3")
}
