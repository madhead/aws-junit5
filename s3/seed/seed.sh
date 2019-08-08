#!/usr/bin/env sh

set -x

aws --endpoint-url ${S3_URL} s3api delete-bucket --bucket bucket || true
aws --endpoint-url ${S3_URL} s3api create-bucket --bucket bucket
