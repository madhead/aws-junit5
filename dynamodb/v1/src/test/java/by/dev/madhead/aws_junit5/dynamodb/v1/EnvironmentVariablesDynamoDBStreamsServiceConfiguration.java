package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

public class EnvironmentVariablesDynamoDBStreamsServiceConfiguration implements ServiceConfiguration {
    @Override
    public String url() {
        return System.getenv("DYNAMODB_STREAMS_URL");
    }

    @Override
    public String region() {
        return System.getenv("DYNAMODB_STREAMS_REGION");
    }

    @Override
    public String accessKey() {
        return System.getenv("DYNAMODB_STREAMS_ACCESS_KEY");
    }

    @Override
    public String secretKey() {
        return System.getenv("DYNAMODB_STREAMS_SECRET_KEY");
    }
}
