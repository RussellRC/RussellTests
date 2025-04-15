package org.russell.tests.flink;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class KafkaAdmin {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    Properties adminProps = new Properties();
    adminProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    try (AdminClient adminClient = AdminClient.create(adminProps)) {
      final DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singleton("input-topic"));
      deleteTopicsResult.topicNameValues().get("input-topic").get();
      System.out.println("topic deleted");

      final CreateTopicsResult result = adminClient.createTopics(Collections.singleton(new NewTopic("input-topic", 3, (short) 1)));
      System.out.println(result.config("input-topic").get());
      System.out.println("topic created");
    }
  }
}
