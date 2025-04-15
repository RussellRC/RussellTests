package org.russell.tests.flink;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class MyKafkaConsumer {

  public static void main(String[] args) {

    // Consumer Properties
    final Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.setProperty(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");

    try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
      System.out.println("Printing records from topic");

      consumer.subscribe(Collections.singleton("input-topic"));
      final ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

      for (final ConsumerRecord<String, String> record : records) {
        System.out.printf("Record %s:%s%n", record.key(), record.value());
      }
    }

    System.out.println("Consume completed");
  }
}
