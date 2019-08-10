#!/usr/bin/env sh

set -x

aws --endpoint-url ${SNS_URL} sns delete-topic --topic-arn topic || true
aws --endpoint-url ${SNS_URL} sns create-topic --name topic
