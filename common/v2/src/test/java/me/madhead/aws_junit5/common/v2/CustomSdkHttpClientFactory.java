package me.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;

class CustomSdkHttpClientFactory implements SdkHttpClientFactory {
    @Override
    public SdkHttpClient create() {
        return ApacheHttpClient
            .builder()
            .maxConnections(42)
            .build();
    }
}
