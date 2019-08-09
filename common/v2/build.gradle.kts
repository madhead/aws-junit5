dependencies {
    api(project(":common"))
    api("software.amazon.awssdk:aws-core")

    testImplementation("software.amazon.awssdk:dynamodb")
    testImplementation("software.amazon.awssdk:s3")
    testImplementation("software.amazon.awssdk:kinesis")
    testImplementation("software.amazon.awssdk:firehose")
}
