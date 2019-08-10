package by.dev.madhead.aws_junit5.common.v1;

import com.amazonaws.ClientConfiguration;

class CustomClientConfigurationFactory implements ClientConfigurationFactory {
    @Override
    public ClientConfiguration create() {
        return new ClientConfiguration()
            .withHeader("test", "https://madhead.me");
    }
}
