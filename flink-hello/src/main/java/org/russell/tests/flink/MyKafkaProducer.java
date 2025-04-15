package org.russell.tests.flink;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MyKafkaProducer {

  static final ObjectMapper MAPPER = new ObjectMapper();

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, JsonProcessingException {

    // Create Producer Properties
    final Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.setProperty(
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.setProperty(
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    // Create the Producer
    KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
    Runtime.getRuntime().addShutdownHook(new Thread(producer::close, "Shutdown-thread"));

    for (int i = 0; i < 5 ; i++) {
      final Map<String, String> payload = Map.of("field1", "hello", "field2", "world" + i);
      final String jsonPayload = MAPPER.writeValueAsString(payload);
      System.out.println("payload: " + jsonPayload);
      final RecordMetadata recordMetadata =
              producer.send(new ProducerRecord<>("input-topic", "key", jsonPayload)).get();
      System.out.println(recordMetadata.offset());
    }

    System.out.println("Producing complete");
  }
}
