package by.dev.madhead.aws_junit5.kinesis.v2;

import by.dev.madhead.aws_junit5.common.v2.SdkHttpClientFactory;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.SdkHttpConfigurationOption;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.utils.AttributeMap;

public class KinesisSdkHttpClientFactory implements SdkHttpClientFactory {
    @Override
    public SdkHttpClient create() {
        return ApacheHttpClient
            .builder()
            .buildWithDefaults(
                AttributeMap
                    .builder()
                    .put(SdkHttpConfigurationOption.TRUST_ALL_CERTIFICATES, Boolean.TRUE)
                    .build()
            );
    }
}
