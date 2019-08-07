package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

public class SystemPropertiesDynamoDBServiceConfiguration implements ServiceConfiguration {
    @Override
    public String url() {
        return System.getProperty("dynamodb.url");
    }

    @Override
    public String region() {
        return System.getProperty("dynamodb.region");
    }

    @Override
    public String accessKey() {
        return System.getProperty("dynamodb.accessKey");
    }

    @Override
    public String secretKey() {
        return System.getProperty("dynamodb.secretKey");
    }
}
