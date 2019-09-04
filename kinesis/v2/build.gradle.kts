dependencies {
    api(project(":common:common-v2"))
    api("software.amazon.awssdk:kinesis")
    api("software.amazon.awssdk:firehose")
    testImplementation("software.amazon.awssdk:netty-nio-client")
    testImplementation("software.amazon.awssdk:apache-client")
}
