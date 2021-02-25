package me.madhead.aws_junit5.common.v2;

import me.madhead.aws_junit5.common.AWSEndpoint;
import software.amazon.awssdk.regions.Region;

class Endpoint implements AWSEndpoint {
    @Override
    public String url() {
        return "https://madhead.me";
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
