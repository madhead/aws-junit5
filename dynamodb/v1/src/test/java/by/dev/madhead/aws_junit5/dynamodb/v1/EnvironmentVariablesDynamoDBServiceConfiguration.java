package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

public class EnvironmentVariablesDynamoDBServiceConfiguration implements ServiceConfiguration {
    @Override
    public String url() {
        return System.getenv("DYNAMODB_URL");
    }

    @Override
    public String region() {
        return System.getenv("DYNAMODB_REGION");
    }

    @Override
    public String accessKey() {
        return System.getenv("DYNAMODB_ACCESS_KEY");
    }

    @Override
    public String secretKey() {
        return System.getenv("DYNAMODB_SECRET_KEY");
    }
}
