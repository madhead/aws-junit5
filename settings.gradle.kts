plugins {
    id("com.gradle.enterprise").version("3.0")
}

rootProject.name = "aws-junit5"

include(
    ":common",
    ":common:v1",
    ":common:v2",
    ":dynamo:v1",
    ":dynamo:v2",
    ":s3:v1",
    ":s3:v2",
    ":kinesis:v1",
    ":kinesis:v2",
    ":sns:v1",
    ":sns:v2",
    ":sqs:v1",
    ":sqs:v2",
    ":ses:v1",
    ":ses:v2",
    ":lambda:v1",
    ":lambda:v2",
)

project(":common:v1").name = "common-v1"
project(":common:v2").name = "common-v2"
project(":dynamo:v1").name = "dynamo-v1"
project(":dynamo:v2").name = "dynamo-v2"
project(":s3:v1").name = "s3-v1"
project(":s3:v2").name = "s3-v2"
project(":kinesis:v1").name = "kinesis-v1"
project(":kinesis:v2").name = "kinesis-v2"
project(":sns:v1").name = "sns-v1"
project(":sns:v2").name = "sns-v2"
project(":sqs:v1").name = "sqs-v1"
project(":sqs:v2").name = "sqs-v2"
project(":ses:v1").name = "ses-v1"
project(":ses:v2").name = "ses-v2"
project(":lambda:v1").name = "lambda-v1"
project(":lambda:v2").name = "lambda-v2"

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
