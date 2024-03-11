package org.streaming.config;


import java.util.Properties;

public class ProducerConfig {

    private final CommonConfig commonConfig= new CommonConfig();

    private final Properties properties = commonConfig.getProperties();
    public Properties getProducerProperties(){
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

}
