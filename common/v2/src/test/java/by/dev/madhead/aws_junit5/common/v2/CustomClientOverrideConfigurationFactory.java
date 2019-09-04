package by.dev.madhead.aws_junit5.common.v2;

import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;

class CustomClientOverrideConfigurationFactory implements ClientOverrideConfigurationFactory {
    @Override
    public ClientOverrideConfiguration create() {
        return ClientOverrideConfiguration
            .builder()
            .putHeader("test", "https://madhead.me")
            .build();
    }
}
