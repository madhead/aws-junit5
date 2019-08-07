package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;
import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.utils.StringUtils;

import java.net.URI;

public abstract class AbstractDynamoDbClientFactory<S extends AwsClientBuilder<S, T>, T> implements DynamoDbClientFactory<T> {
    private final AwsClientBuilder<S, T> awsClientBuilder;

    protected AbstractDynamoDbClientFactory(final AwsClientBuilder<S, T> awsClientBuilder) {
        this.awsClientBuilder = awsClientBuilder;
    }

    @Override
    public T createClient(final DynamoDBLocal configuration) throws Exception {
        final ServiceConfiguration serviceConfiguration = configuration.serviceConfiguration().newInstance();

        validate(serviceConfiguration);

        return awsClientBuilder
            .endpointOverride(new URI(serviceConfiguration.url()))
            .region(Region.of(serviceConfiguration.region()))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(serviceConfiguration.accessKey(), serviceConfiguration.secretKey())
                )
            )
            .build();
    }

    private void validate(final ServiceConfiguration serviceConfiguration) {
        if ((serviceConfiguration == null) || (StringUtils.isEmpty(serviceConfiguration.url()))) {
            throw new IllegalArgumentException("Missing URL");
        }
    }
}
