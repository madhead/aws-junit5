dependencies {
    // By default, the code is compiled against the lowest supported AWS SDK versions.
    // If ME_MADHEAD_AWS_JUNIT5_USE_LATEST_AWS_SDK environment variable is set to true we want to test the
    // code against the latest available AWS SDK versions.
    val v2: String = if (System.getenv("ME_MADHEAD_AWS_JUNIT5_USE_LATEST_AWS_SDK") == "true") {
        "2.+"
    } else {
        "2.5.3"
    }

    api(platform("software.amazon.awssdk:bom:$v2"))
    api(project(":common"))
    api("software.amazon.awssdk:aws-core")

    testImplementation("software.amazon.awssdk:dynamodb")
    testImplementation("software.amazon.awssdk:s3")
    testImplementation("software.amazon.awssdk:kinesis")
    testImplementation("software.amazon.awssdk:firehose")
    testImplementation("software.amazon.awssdk:sns")
    testImplementation("software.amazon.awssdk:sqs")
    testImplementation("software.amazon.awssdk:ses")
    testImplementation("software.amazon.awssdk:lambda")
    testImplementation("software.amazon.awssdk:netty-nio-client")
    testImplementation("software.amazon.awssdk:apache-client")
}
