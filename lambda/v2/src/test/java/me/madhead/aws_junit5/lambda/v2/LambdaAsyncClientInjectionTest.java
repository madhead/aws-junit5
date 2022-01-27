package me.madhead.aws_junit5.lambda.v2;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.model.FunctionConfiguration;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Lambda.class)
class LambdaAsyncClientInjectionTest {
    @AWSClient(
        endpoint = LambdaClientInjectionTest.Endpoint.class
    )
    private LambdaAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.emptyList(),
            client
                .listFunctions()
                .get()
                .functions()
                .stream()
                .map(FunctionConfiguration::functionName)
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
