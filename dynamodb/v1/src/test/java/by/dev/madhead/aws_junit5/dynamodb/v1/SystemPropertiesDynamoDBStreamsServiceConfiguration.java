package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

public class SystemPropertiesDynamoDBStreamsServiceConfiguration implements ServiceConfiguration {
    @Override
    public String url() {
        return System.getProperty("dynamodbstreams.url");
    }

    @Override
    public String region() {
        return System.getProperty("dynamodbstreams.region");
    }

    @Override
    public String accessKey() {
        return System.getProperty("dynamodbstreams.accessKey");
    }

    @Override
    public String secretKey() {
        return System.getProperty("dynamodbstreams.secretKey");
    }
}
