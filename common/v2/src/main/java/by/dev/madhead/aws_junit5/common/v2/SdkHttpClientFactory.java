package by.dev.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.http.SdkHttpClient;

/**
 * Creates {@link SdkHttpClient} for AWS async clients.
 */
public interface SdkHttpClientFactory {
    /**
     * {@link SdkHttpClient} for an AWS async client. Return {@code null} if the default client should be used.
     *
     * @return {@link SdkHttpClient} for an AWS async client. {@code null} if the default client should be used.
     */
    SdkHttpClient create();
}
