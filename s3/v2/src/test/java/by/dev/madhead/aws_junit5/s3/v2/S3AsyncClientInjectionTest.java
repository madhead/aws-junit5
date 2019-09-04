package by.dev.madhead.aws_junit5.s3.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(S3.class)
class S3AsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private S3AsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("bucket"),
            client
                .listBuckets()
                .get()
                .buckets()
                .stream()
                .map(Bucket::name)
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("s3.url");
        }

        @Override
        public String region() {
            return System.getProperty("s3.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("s3.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("s3.secretKey");
        }
    }
}
