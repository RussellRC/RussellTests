FROM flink:1.19.2-scala_2.12-java17

RUN wget -P /opt/flink/lib https://repo.maven.apache.org/maven2/org/apache/flink/flink-connector-kafka/3.2.0-1.19/flink-connector-kafka-3.2.0-1.19.jar  && \
    wget -P /opt/flink/lib https://repo1.maven.org/maven2/org/apache/flink/flink-streaming-java/1.19.2/flink-streaming-java-1.19.2.jar && \
    wget -P /opt/flink/lib https://repo1.maven.org/maven2/org/apache/kafka/kafka-clients/3.9.0/kafka-clients-3.9.0.jar
