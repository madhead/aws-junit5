package by.dev.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;

class CustomSdkAsyncHttpClientFactory implements SdkAsyncHttpClientFactory {
    @Override
    public SdkAsyncHttpClient create() {
        return NettyNioAsyncHttpClient
            .builder()
            .maxConcurrency(42)
            .build();
    }
}
