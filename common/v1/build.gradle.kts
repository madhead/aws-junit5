dependencies {
    api(project(":common"))
    api("com.amazonaws:aws-java-sdk-core")

    testImplementation("com.amazonaws:aws-java-sdk-dynamodb")
    testImplementation("com.amazonaws:aws-java-sdk-s3")
    testImplementation("com.amazonaws:aws-java-sdk-kinesis")
    testImplementation("com.amazonaws:aws-java-sdk-sns")
    testImplementation("com.amazonaws:aws-java-sdk-sqs")
    testImplementation("com.amazonaws:aws-java-sdk-ses")
}
