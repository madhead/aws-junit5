package me.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;

/**
 * Creates {@link ClientOverrideConfiguration} for AWS clients.
 */
public interface ClientOverrideConfigurationFactory {
    /**
     * {@link ClientOverrideConfiguration} for an AWS client. Return {@code null} if the default configuration should be used.
     *
     * @return {@link ClientOverrideConfiguration} for an AWS client. {@code null} if the default configuration should be used.
     */
    ClientOverrideConfiguration create();
}
