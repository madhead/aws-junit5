package me.madhead.aws_junit5.kinesis.v2;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import me.madhead.aws_junit5.common.v2.AWSAdvancedConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.kinesis.KinesisClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
class KinesisClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    @AWSAdvancedConfiguration(
        sdkHttpClientFactory = KinesisSdkHttpClientFactory.class
    )
    private KinesisClient client;

    @BeforeEach
    void disableCBOR() {
        System.setProperty("aws.cborEnabled", "false");
        System.setProperty("com.amazonaws.sdk.disableCbor", "true");
    }

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("stream"),
            client
                .listStreams()
                .streamNames()
                .stream()
                .filter(s -> !s.startsWith("__"))
                .sorted()
                .collect(Collectors.toList())
        );
    }

    @AfterEach
    void enableCBOR() {
        System.setProperty("aws.cborEnabled", "true");
        System.setProperty("com.amazonaws.sdk.disableCbor", "false");
    }

    public static class Endpoint implements AWSEndpoint {
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
