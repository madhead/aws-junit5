package me.madhead.aws_junit5.lambda.v1;

import com.amazonaws.services.lambda.AWSLambda;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Lambda.class)
class AWSLambdaInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AWSLambda client;

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.emptyList(),
            client
                .listFunctions()
                .getFunctions()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getenv("LAMBDA_URL");
        }

        @Override
        public String region() {
            return System.getenv("LAMBDA_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("LAMBDA_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("LAMBDA_SECRET_KEY");
        }
    }
}
