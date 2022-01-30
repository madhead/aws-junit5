#!/usr/bin/env sh

set -x

aws --endpoint-url "${SES_URL}" ses verify-email-identity --email-address madhead@madhead.me
