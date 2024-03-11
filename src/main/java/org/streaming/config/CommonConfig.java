package org.streaming.config;

import java.util.Properties;

public class CommonConfig {

    CommonConfig(){}
    private Properties commonProperties = new Properties();

    public Properties getProperties(){
        commonProperties.put("bootstrap.servers", "https://rapid-lemur-5625-us1-kafka.upstash.io:9092");
        commonProperties.put("sasl.mechanism", "SCRAM-SHA-256");
        commonProperties.put("security.protocol", "SASL_SSL");
        commonProperties.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"cmFwaWQtbGVtdXItNTYyNSTjgMWMiOizzzuEd8FWOjauTGEc0XFkr85QSGNQSR8\" password=\"MTQzYWEwMGYtMjUxNS00ZDk3LTk1OWUtMDc1MDQzNmUyYjQ4\";");
        return commonProperties;
    }
}
