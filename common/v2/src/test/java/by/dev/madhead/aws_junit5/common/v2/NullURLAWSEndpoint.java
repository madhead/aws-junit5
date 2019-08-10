package by.dev.madhead.aws_junit5.common.v2;

import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import software.amazon.awssdk.regions.Region;

class NullURLAWSEndpoint implements AWSEndpoint {
    @Override
    public String url() {
        return null;
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
