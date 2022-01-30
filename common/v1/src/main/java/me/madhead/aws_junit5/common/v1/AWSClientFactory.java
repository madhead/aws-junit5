package me.madhead.aws_junit5.common.v1;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.util.StringUtils;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;

import java.lang.reflect.Field;

/**
 * Creates AWS Java SDK v 1.x clients of type {@link T}.
 *
 * @param <S>
 *     the type of {@link AwsClientBuilder}, who actually creates AWS clients of type {@link T}.
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
        final AWSEndpoint endpoint = awsClientAnnotation.endpoint().getDeclaredConstructor().newInstance();

        validate(endpoint);

        final S awsClientBuilder = this.awsClientBuilder
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                    endpoint.url(),
                    endpoint.region()
                )
            )
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(endpoint.accessKey(), endpoint.secretKey())
                )
            );

        if (field.isAnnotationPresent(AWSAdvancedConfiguration.class)) {
            final AWSAdvancedConfiguration awsAdvancedConfiguration = field.getAnnotation(AWSAdvancedConfiguration.class);
            final ClientConfiguration clientConfiguration =
                awsAdvancedConfiguration.clientConfigurationFactory().getDeclaredConstructor().newInstance().create();

            if (clientConfiguration != null) {
                awsClientBuilder.withClientConfiguration(clientConfiguration);
            }
        }

        return awsClientBuilder.build();
    }

    private void validate(final AWSEndpoint clientConfiguration) {
        if (StringUtils.isNullOrEmpty(clientConfiguration.url())) {
            throw new IllegalArgumentException("Missing URL");
        }
    }
}
