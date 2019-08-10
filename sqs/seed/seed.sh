#!/usr/bin/env sh

set -x

aws --endpoint-url ${SQS_URL} sqs delete-queue --queue-url queue || true
aws --endpoint-url ${SQS_URL} sqs create-queue --queue-name queue
