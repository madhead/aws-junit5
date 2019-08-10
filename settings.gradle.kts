rootProject.name = "aws-junit5"

include(
    ":common",
    ":common:v1",
    ":common:v2",
    ":dynamodb:v1",
    ":dynamodb:v2",
    ":s3:v1",
    ":s3:v2",
    ":kinesis:v1",
    ":kinesis:v2",
    ":sns:v1",
    ":sns:v2",
    ":sqs:v1",
    ":sqs:v2"
)

project(":common:v1").name = "common-v1"
project(":common:v2").name = "common-v2"
project(":dynamodb:v1").name = "dynamodb-v1"
project(":dynamodb:v2").name = "dynamodb-v2"
project(":s3:v1").name = "s3-v1"
project(":s3:v2").name = "s3-v2"
project(":kinesis:v1").name = "kinesis-v1"
project(":kinesis:v2").name = "kinesis-v2"
project(":sns:v1").name = "sns-v1"
project(":sns:v2").name = "sns-v2"
project(":sqs:v1").name = "sqs-v1"
project(":sqs:v2").name = "sqs-v2"
