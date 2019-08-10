package by.dev.madhead.aws_junit5.common.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.utils.StringUtils;

import java.lang.reflect.Field;
import java.net.URI;

public final class AWSClientFactory<S extends AwsClientBuilder<S, T>, T> implements by.dev.madhead.aws_junit5.common.AWSClientFactory<T> {
    private final AwsClientBuilder<S, T> awsClientBuilder;

    public AWSClientFactory(final AwsClientBuilder<S, T> awsClientBuilder) {
        this.awsClientBuilder = awsClientBuilder;
    }

    @Override
    public T createClient(final Field field, final AWSClient configuration) throws Exception {
        final AWSClientConfiguration clientConfiguration = configuration.clientConfiguration().newInstance();

        validate(clientConfiguration);

        return awsClientBuilder
            .endpointOverride(new URI(clientConfiguration.url()))
            .region(Region.of(clientConfiguration.region()))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(clientConfiguration.accessKey(), clientConfiguration.secretKey())
                )
            )
            .build();
    }

    private void validate(final AWSClientConfiguration clientConfiguration) {
        if ((clientConfiguration.url() == null) || (StringUtils.isEmpty(clientConfiguration.url()))) {
            throw new IllegalArgumentException("Missing URL");
        }
    }
}
