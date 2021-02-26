package me.madhead.aws_junit5.common;

/**
 * AWS service endpoint configuration.
 */
public interface AWSEndpoint {
    /**
     * AWS service URL.
     *
     * @return AWS service URL.
     */
    String url();

    /**
     * AWS service region.
     *
     * @return AWS service region.
     */
    String region();

    /**
     * AWS service access key.
     *
     * @return AWS service access key.
     */
    String accessKey();

    /**
     * AWS service secret key.
     *
     * @return AWS service secret key.
     */
    String secretKey();
}
