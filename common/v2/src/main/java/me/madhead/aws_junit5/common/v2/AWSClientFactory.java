package me.madhead.aws_junit5.common.v2;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.client.builder.AwsAsyncClientBuilder;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.awscore.client.builder.AwsSyncClientBuilder;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.utils.StringUtils;

import java.lang.reflect.Field;
import java.net.URI;

/**
 * Creates AWS Java SDK v 2.x clients of type {@link T}.
 *
 * @param <S>
 *     the type of an {@link AwsClientBuilder}, who actually creates AWS clients of type {@link T}.
 * @param <T>
 *     the type of AWS clients this factory creates.
 */
public final class AWSClientFactory<S extends AwsClientBuilder<S, T>, T> implements me.madhead.aws_junit5.common.impl.AWSClientFactory<T> {
    private final AwsClientBuilder<S, T> awsClientBuilder;

    public AWSClientFactory(final AwsClientBuilder<S, T> awsClientBuilder) {
        this.awsClientBuilder = awsClientBuilder;
    }

    @Override
    public T client(final Field field) throws Exception {
        final AWSClient awsClientAnnotation = field.getAnnotation(AWSClient.class);
        final AWSEndpoint endpoint = awsClientAnnotation.endpoint().newInstance();

        validate(endpoint);

        final S awsClientBuilder = this.awsClientBuilder
            .endpointOverride(new URI(endpoint.url()))
            .region(Region.of(endpoint.region()))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(endpoint.accessKey(), endpoint.secretKey())
                )
            );

        if (field.isAnnotationPresent(AWSAdvancedConfiguration.class)) {
            final AWSAdvancedConfiguration awsAdvancedConfiguration = field.getAnnotation(AWSAdvancedConfiguration.class);
            final ClientOverrideConfiguration clientOverrideConfiguration =
                awsAdvancedConfiguration.clientOverrideConfigurationFactory().newInstance().create();

            if (null != clientOverrideConfiguration) {
                awsClientBuilder.overrideConfiguration(clientOverrideConfiguration);
            }

            if (awsClientBuilder instanceof AwsSyncClientBuilder) {
                final SdkHttpClient sdkHttpClient = awsAdvancedConfiguration.sdkHttpClientFactory().newInstance().create();

                if (null != sdkHttpClient) {
                    ((AwsSyncClientBuilder) awsClientBuilder).httpClient(sdkHttpClient);
                }
            }

            if (awsClientBuilder instanceof AwsAsyncClientBuilder) {
                final SdkAsyncHttpClient sdkAsyncHttpClient = awsAdvancedConfiguration.sdkAsyncHttpClientFactory().newInstance().create();

                if (null != sdkAsyncHttpClient) {
                    ((AwsAsyncClientBuilder) awsClientBuilder).httpClient(sdkAsyncHttpClient);
                }
            }
        }

        return awsClientBuilder.build();
    }

    private void validate(final AWSEndpoint clientConfiguration) {
        if ((clientConfiguration.url() == null) || (StringUtils.isEmpty(clientConfiguration.url()))) {
            throw new IllegalArgumentException("Missing URL");
        }
    }
}
