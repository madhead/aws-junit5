package me.madhead.aws_junit5.dynamo.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

//tag::usage[]
@ExtendWith(DynamoDB.class)
class AmazonDynamoDBInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonDynamoDB client;

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client.listTables().getTableNames().stream().sorted().collect(Collectors.toList())
        );
    }
    //end::usage[]

    //tag::endpoint[]
    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getenv("DYNAMODB_URL");
        }

        @Override
        public String region() {
            return System.getenv("DYNAMODB_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("DYNAMODB_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("DYNAMODB_SECRET_KEY");
        }
    }
    //end::endpoint[]
}
