package me.madhead.aws_junit5.lambda.v2;

import java.util.Collections;
import java.util.stream.Collectors;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.FunctionConfiguration;

@ExtendWith(Lambda.class)
class LambdaClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private LambdaClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.emptyList(),
            client
                .listFunctions()
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
