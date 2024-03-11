package org.streaming.config;

import java.util.Properties;

public class ConsumerConfig {
    private final CommonConfig commonConfig= new CommonConfig();

    private final Properties properties = commonConfig.getProperties();
    public Properties getConsumerProperties(){
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "upstack-gp1");
        properties.put("auto.offset.reset", "earliest");
        return properties;
    }
}
