package by.dev.madhead.aws_junit5.kinesis.v2;

import by.dev.madhead.aws_junit5.common.v2.SdkAsyncHttpClientFactory;
import software.amazon.awssdk.http.Protocol;
import software.amazon.awssdk.http.SdkHttpConfigurationOption;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.utils.AttributeMap;

public class KinesisSdkAsyncHttpClientFactory implements SdkAsyncHttpClientFactory {
    @Override
    public SdkAsyncHttpClient create() {
        return NettyNioAsyncHttpClient
            .builder()
            .protocol(Protocol.HTTP1_1)
            .buildWithDefaults(
                AttributeMap
                    .builder()
                    .put(SdkHttpConfigurationOption.TRUST_ALL_CERTIFICATES, java.lang.Boolean.TRUE)
                    .build()
            );
    }
}
