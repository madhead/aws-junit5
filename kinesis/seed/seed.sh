#!/usr/bin/env sh

set -x

aws --endpoint-url "${KINESIS_URL}" kinesis delete-stream --stream-name stream || true
aws --endpoint-url "${KINESIS_URL}" kinesis create-stream --stream-name stream --shard-count 1

aws --endpoint-url "${FIREHOSE_URL}" firehose delete-delivery-stream --delivery-stream-name delivery-stream || true
aws --endpoint-url "${FIREHOSE_URL}" firehose create-delivery-stream --delivery-stream-name delivery-stream
