package me.madhead.aws_junit5.common.v1;

import com.amazonaws.regions.Regions;
import me.madhead.aws_junit5.common.AWSEndpoint;

class Endpoint implements AWSEndpoint {
    @Override
    public String url() {
        return "https://madhead.me";
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
