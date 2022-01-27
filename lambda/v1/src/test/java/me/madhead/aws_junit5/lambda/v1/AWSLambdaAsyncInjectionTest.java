package me.madhead.aws_junit5.lambda.v1;

import com.amazonaws.services.lambda.AWSLambdaAsync;
import java.util.Collections;
import java.util.stream.Collectors;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Lambda.class)
class AWSLambdaAsyncInjectionTest {
    @AWSClient(
        endpoint = AWSLambdaInjectionTest.Endpoint.class
    )
    private AWSLambdaAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.emptyList(),
            client
                .listFunctionsAsync()
                .get()
                .getFunctions()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("lambda.url");
        }

        @Override
        public String region() {
            return System.getProperty("lambda.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("lambda.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("lambda.secretKey");
        }
    }
}
