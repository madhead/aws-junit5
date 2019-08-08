package by.dev.madhead.aws_junit5.common;

/**
 * AWS service configuration provider.
 */
public interface AWSClientConfiguration {
    /**
     * Service URL.
     *
     * @return Service URL.
     */
    String url();

    /**
     * AWS region.
     *
     * @return AWS region.
     */
    String region();

    /**
     * AWS access key.
     *
     * @return AWS access key.
     */
    String accessKey();

    /**
     * AWS secret key.
     *
     * @return AWS secret key.
     */
    String secretKey();
}
