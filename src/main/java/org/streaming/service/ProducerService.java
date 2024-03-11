package org.streaming.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.streaming.config.ProducerConfig;
import org.streaming.utils.Constants.*;

import static org.streaming.utils.Constants.FIRST_TOPIC;

@Slf4j
public class ProducerService {

    private final ProducerConfig producerConfig = new ProducerConfig();



    public void produceData(){

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(producerConfig.getProducerProperties());
        log.info("Producer configurations "+producerConfig.getProducerProperties());
        for(int i = 0;i<10;i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(FIRST_TOPIC, String.valueOf(i));

            kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if(exception == null){
                            log.info("Message to Topic: "+metadata.topic()+" with Partition :"+metadata.partition()+" at timestamp "+metadata.timestamp());
                        }
                        else{
                            log.error("Message Failed with exception "+exception.getMessage());
                        }

                }
            });

        }
        kafkaProducer.close();

    }
}
