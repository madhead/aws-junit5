dependencies {
    // By default, the code is compiled against the lowest supported AWS SDK versions.
    // If ME_MADHEAD_AWS_JUNIT5_USE_LATEST_AWS_SDK environment variable is set to true we want to test the
    // code against the latest available AWS SDK versions.
    val v1: String = if (System.getenv("ME_MADHEAD_AWS_JUNIT5_USE_LATEST_AWS_SDK") == "true") {
        "1.+"
    } else {
        "1.11.79"
    }

    api(platform("com.amazonaws:aws-java-sdk-bom:$v1"))
    api(project(":common"))
    api("com.amazonaws:aws-java-sdk-core")

    testImplementation("com.amazonaws:aws-java-sdk-dynamodb")
    testImplementation("com.amazonaws:aws-java-sdk-s3")
    testImplementation("com.amazonaws:aws-java-sdk-kinesis")
    testImplementation("com.amazonaws:aws-java-sdk-sns")
    testImplementation("com.amazonaws:aws-java-sdk-sqs")
    testImplementation("com.amazonaws:aws-java-sdk-ses")
}
