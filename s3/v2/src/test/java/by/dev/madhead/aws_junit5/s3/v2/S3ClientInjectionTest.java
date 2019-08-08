package by.dev.madhead.aws_junit5.s3.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(S3.class)
class S3ClientInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private S3Client client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("bucket"),
            client
                .listBuckets()
                .buckets()
                .stream()
                .map(Bucket::name)
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getenv("S3_URL");
        }

        @Override
        public String region() {
            return System.getenv("S3_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("S3_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("S3_SECRET_KEY");
        }
    }
}
