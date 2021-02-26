package me.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use {@link AWSAdvancedConfiguration} to provide custom {@link ClientOverrideConfiguration} and / or {@link SdkAsyncHttpClient} for
 * injected clients.
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface AWSAdvancedConfiguration {
    /**
     * {@link ClientOverrideConfiguration} factory class.
     *
     * @return {@link ClientOverrideConfiguration} factory class.
     */
    Class<? extends ClientOverrideConfigurationFactory> clientOverrideConfigurationFactory() default DefaultClientOverrideConfigurationFactory.class;

    /**
     * {@link SdkHttpClient} factory class.
     *
     * @return {@link SdkHttpClient} factory class.
     */
    Class<? extends SdkHttpClientFactory> sdkHttpClientFactory() default DefaultSdkHttpClientFactory.class;

    /**
     * {@link SdkAsyncHttpClient} factory class.
     *
     * @return {@link SdkAsyncHttpClient} factory class.
     */
    Class<? extends SdkAsyncHttpClientFactory> sdkAsyncHttpClientFactory() default DefaultSdkAsyncHttpClientFactory.class;

    class DefaultClientOverrideConfigurationFactory implements ClientOverrideConfigurationFactory {
        @Override
        public ClientOverrideConfiguration create() {
            return null;
        }
    }

    class DefaultSdkHttpClientFactory implements SdkHttpClientFactory {
        @Override
        public SdkHttpClient create() {
            return null;
        }
    }

    class DefaultSdkAsyncHttpClientFactory implements SdkAsyncHttpClientFactory {
        @Override
        public SdkAsyncHttpClient create() {
            return null;
        }
    }
}
