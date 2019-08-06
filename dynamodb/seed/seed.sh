#!/usr/bin/env sh

set -x

aws --endpoint-url ${BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_1} dynamodb delete-table --table-name table_1 || true
aws --endpoint-url ${BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_2} dynamodb delete-table --table-name table_2 || true
aws --endpoint-url ${BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_1} dynamodb create-table --cli-input-json file://dynamodb/seed/table_1.table.json
aws --endpoint-url ${BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_2} dynamodb create-table --cli-input-json file://dynamodb/seed/table_2.table.json

for seed in dynamodb/seed/table_1.table/*.item.json; do
    aws --endpoint-url ${BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_1} dynamodb put-item --cli-input-json file://${seed}
done

for seed in dynamodb/seed/table_2.table/*.item.json; do
    aws --endpoint-url ${BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_2} dynamodb put-item --cli-input-json file://${seed}
done
