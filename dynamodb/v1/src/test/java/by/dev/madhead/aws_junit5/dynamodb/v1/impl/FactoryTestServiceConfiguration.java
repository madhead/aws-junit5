package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;
import com.amazonaws.regions.Regions;

class FactoryTestServiceConfiguration implements ServiceConfiguration {
    @Override
    public String url() {
        return "http://localhost:8000";
    }

    @Override
    public String region() {
        return Regions.US_EAST_1.getName();
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
