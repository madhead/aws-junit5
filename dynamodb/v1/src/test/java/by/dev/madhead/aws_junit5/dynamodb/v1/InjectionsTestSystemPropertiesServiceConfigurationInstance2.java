package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

public class InjectionsTestSystemPropertiesServiceConfigurationInstance2 implements ServiceConfiguration {
    @Override
    public String url() {
        return System.getProperty("by.dev.madhead.aws_junit5.dynamodb.v1.url2");
    }

    @Override
    public String region() {
        return System.getProperty("by.dev.madhead.aws_junit5.dynamodb.v1.region2");
    }

    @Override
    public String accessKey() {
        return System.getProperty("by.dev.madhead.aws_junit5.dynamodb.v1.accessKey2");
    }

    @Override
    public String secretKey() {
        return System.getProperty("by.dev.madhead.aws_junit5.dynamodb.v1.secretKey2");
    }
}
