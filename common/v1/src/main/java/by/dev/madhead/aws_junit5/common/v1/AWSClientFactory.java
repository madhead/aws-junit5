package by.dev.madhead.aws_junit5.common.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.util.StringUtils;

public final class AWSClientFactory<S extends AwsClientBuilder<S, T>, T> implements by.dev.madhead.aws_junit5.common.AWSClientFactory<T> {
    private final AwsClientBuilder<S, T> awsClientBuilder;

    public AWSClientFactory(final AwsClientBuilder<S, T> awsClientBuilder) {
        this.awsClientBuilder = awsClientBuilder;
    }

    @Override
    public T createClient(final AWSClient configuration) throws Exception {
        final AWSClientConfiguration clientConfiguration = configuration.clientConfiguration().newInstance();

        validate(clientConfiguration);

        return awsClientBuilder
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                    clientConfiguration.url(),
                    clientConfiguration.region()
                )
            )
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(clientConfiguration.accessKey(), clientConfiguration.secretKey())
                )
            )
            .build();
    }

    private void validate(final AWSClientConfiguration clientConfiguration) {
        if (StringUtils.isNullOrEmpty(clientConfiguration.url())) {
            throw new IllegalArgumentException("Missing URL");
        }
    }
}
