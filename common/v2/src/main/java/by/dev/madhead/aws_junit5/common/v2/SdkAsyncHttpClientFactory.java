package by.dev.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.http.async.SdkAsyncHttpClient;

/**
 * Creates {@link SdkAsyncHttpClient} for AWS async clients.
 */
public interface SdkAsyncHttpClientFactory {
    /**
     * {@link SdkAsyncHttpClient} for an AWS async client. Return {@code null} if the default client should be used.
     *
     * @return {@link SdkAsyncHttpClient} for an AWS async client. {@code null} if the default client should be used.
     */
    SdkAsyncHttpClient create();
}
