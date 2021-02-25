package me.madhead.aws_junit5.common.v1;

import com.amazonaws.ClientConfiguration;

/**
 * Creates {@link ClientConfiguration} for AWS clients.
 */
public interface ClientConfigurationFactory {
    /**
     * {@link ClientConfiguration} for an AWS client. Return {@code null} if the default configuration should be used.
     *
     * @return {@link ClientConfiguration} for an AWS client. {@code null} if the default configuration should be used.
     */
    ClientConfiguration create();
}
