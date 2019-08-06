package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;
import java.util.stream.Collectors;


@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBInjectionsTest {
    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestEnvironmentVariablesServiceConfigurationInstance1.class
    )
    private AmazonDynamoDB amazonDynamoDBByEnvironmentVariables;

    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestSystemPropertiesServiceConfigurationInstance2.class
    )
    private AmazonDynamoDB amazonDynamoDBBySystemProperties;

    @Test
    @DisplayName("AmazonDynamoDB should be injected and configured properly with environment variables")
    void test1() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBByEnvironmentVariables);

        Assertions.assertEquals(
            Collections.singletonList("table_1"),
            amazonDynamoDBByEnvironmentVariables.listTables().getTableNames().stream().sorted().collect(Collectors.toList())
        );
        Assertions.assertEquals(
            new LinkedList<HashMap<String, AttributeValue>>() {{
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_1-001"));
                        put("data", new AttributeValue("unit-test-table_1-001"));
                    }}
                );
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_1-002"));
                        put("data", new AttributeValue("unit-test-table_1-002"));
                    }}
                );
            }},
            amazonDynamoDBByEnvironmentVariables
                .scan("table_1", (List<String>) null)
                .getItems()
                .stream()
                .sorted(Comparator.comparing(item -> item.get("id").getS()))
                .collect(Collectors.toList())
        );
    }

    @Test
    @DisplayName("AmazonDynamoDBAsync should be injected and configured properly with system properties")
    void test2() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBBySystemProperties);

        Assertions.assertEquals(
            Collections.singletonList("table_2"),
            amazonDynamoDBBySystemProperties.listTables().getTableNames().stream().sorted().collect(Collectors.toList())
        );
        Assertions.assertEquals(
            new LinkedList<HashMap<String, AttributeValue>>() {{
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_2-001"));
                        put("data", new AttributeValue("unit-test-table_2-001"));
                    }}
                );
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_2-002"));
                        put("data", new AttributeValue("unit-test-table_2-002"));
                    }}
                );
            }},
            amazonDynamoDBBySystemProperties
                .scan("table_2", (List<String>) null)
                .getItems()
                .stream()
                .sorted(Comparator.comparing(item -> item.get("id").getS()))
                .collect(Collectors.toList())
        );
    }
}
