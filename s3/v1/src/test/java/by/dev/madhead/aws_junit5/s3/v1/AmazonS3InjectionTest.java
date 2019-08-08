package by.dev.madhead.aws_junit5.s3.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(S3.class)
class AmazonS3InjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonS3 client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("bucket"),
            client.listBuckets().stream().map(Bucket::getName).sorted().collect(Collectors.toList())
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
