package by.dev.madhead.aws_junit5.kinesis.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.kinesis.KinesisClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
@Disabled("AWS Java SDK 2.x does not work well with Kinesalite (part of localstack providing Kinesis API)")
class KinesisClientInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private KinesisClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("stream"),
            client
                .listStreams()
                .streamNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getenv("KINESIS_URL");
        }

        @Override
        public String region() {
            return System.getenv("KINESIS_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("KINESIS_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("KINESIS_SECRET_KEY");
        }
    }
}
