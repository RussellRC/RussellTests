package org.russell.tests.flink;

import java.util.Properties;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.ConsumerConfig;

public class FlinkJob1 {

  public static void main(String[] args) throws Exception {

    // Set up the Flink execution environment
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    final Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9093");
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "flink-group-id");

    // Create a Kafka source
    final KafkaSource<String> source =
        KafkaSource.<String>builder()
            .setProperties(properties)
            .setTopics("input-topic")
            .setValueOnlyDeserializer(new SimpleStringSchema())
            .setStartingOffsets(OffsetsInitializer.earliest())
            .build();

    // Build the data stream
    final DataStream<String> stream =
        env.fromSource(source, WatermarkStrategy.forMonotonousTimestamps(), "Kafka Source");

    stream.print();

    env.execute("Flink Kafka job");
  }
}
