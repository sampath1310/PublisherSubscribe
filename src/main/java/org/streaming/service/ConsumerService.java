package org.streaming.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.streaming.config.ConsumerConfig;

import java.time.Duration;
import java.util.Arrays;

import static org.streaming.utils.Constants.FIRST_TOPIC;

@Slf4j
public class ConsumerService {

    private final ConsumerConfig  consumerConfig = new ConsumerConfig();


    public void consumeData(){
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(consumerConfig.getConsumerProperties());

        final Thread mainThread = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                log.info("Shutdown detected");
                consumer.wakeup();

                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        consumer.subscribe(Arrays.asList(FIRST_TOPIC));
        try {

            while (true) {
                log.info("Polling");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                }

            }
        }catch (WakeupException wakeupException){
            log.info("Consumer is starting to shutdown");
        }catch (Exception e){
            log.error("Consumer failed with Exception ",e);
        }finally {
            consumer.close();
            log.info("Consumer Gracefully shutdown");
        }
    }
}
