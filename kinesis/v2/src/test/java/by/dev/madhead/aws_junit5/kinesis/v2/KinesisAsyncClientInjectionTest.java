package by.dev.madhead.aws_junit5.kinesis.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import by.dev.madhead.aws_junit5.common.v2.AWSAdvancedConfiguration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
@Disabled("AWS Java SDK 2.x does not work well with Kinesalite (part of localstack providing Kinesis API)")
class KinesisAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    @AWSAdvancedConfiguration(
        sdkAsyncHttpClientFactory = KinesisSdkAsyncHttpClientFactory.class
    )
    private KinesisAsyncClient client;

    @BeforeEach
    void disableCBOR() {
        System.setProperty("aws.cborEnabled", "false");
        System.setProperty("com.amazonaws.sdk.disableCbor", "true");
    }

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

    @AfterEach
    void enableCBOR() {
        System.setProperty("aws.cborEnabled", "true");
        System.setProperty("com.amazonaws.sdk.disableCbor", "false");
    }

    public static class Endpoint implements AWSEndpoint {
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
