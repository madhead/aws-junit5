package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

public class InjectionsTestEnvironmentVariablesServiceConfigurationInstance2 implements ServiceConfiguration {
    @Override
    public String url() {
        return System.getenv("BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL_2");
    }

    @Override
    public String region() {
        return System.getenv("BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_REGION_2");
    }

    @Override
    public String accessKey() {
        return System.getenv("BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_ACCESS_KEY_2");
    }

    @Override
    public String secretKey() {
        return System.getenv("BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_SECRET_KEY_2");
    }
}
