#!/usr/bin/env sh

set -x

aws --endpoint-url ${DYNAMODB_URL} dynamodb delete-table --table-name table || true
aws --endpoint-url ${DYNAMODB_URL} dynamodb create-table --cli-input-json file://dynamo/seed/table.table.json

aws --endpoint-url ${DYNAMODB_STREAMS_URL} dynamodb delete-table --table-name table || true
aws --endpoint-url ${DYNAMODB_STREAMS_URL} dynamodb create-table --cli-input-json file://dynamo/seed/table.table.json
