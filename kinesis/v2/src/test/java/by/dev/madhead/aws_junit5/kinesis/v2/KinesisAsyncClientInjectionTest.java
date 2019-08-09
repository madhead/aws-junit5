package by.dev.madhead.aws_junit5.kinesis.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
@Disabled("AWS Java SDK 2.x does not work well with Kinesalite (part of localstack providing Kinesis API)")
class KinesisAsyncClientInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private KinesisAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("stream"),
            client
                .listStreams()
                .get()
                .streamNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getProperty("kinesis.url");
        }

        @Override
        public String region() {
            return System.getProperty("kinesis.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("kinesis.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("kinesis.secretKey");
        }
    }
}
