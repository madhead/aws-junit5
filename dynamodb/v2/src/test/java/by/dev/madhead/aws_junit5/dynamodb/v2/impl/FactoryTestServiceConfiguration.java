package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;
import software.amazon.awssdk.regions.Region;

class FactoryTestServiceConfiguration implements ServiceConfiguration {
    @Override
    public String url() {
        return "http://localstack:4569";
    }

    @Override
    public String region() {
        return Region.US_EAST_1.id();
    }

    @Override
    public String accessKey() {
        return "accessKey";
    }

    @Override
    public String secretKey() {
        return "secretKey";
    }
}
