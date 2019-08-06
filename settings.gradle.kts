rootProject.name = "aws-junit5"

include(":common")
include(":dynamodb:v1")
include(":dynamodb:v2")

project(":dynamodb:v1").name = "dynamodb-v1"
project(":dynamodb:v2").name = "dynamodb-v2"
