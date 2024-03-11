package org.streaming;

import org.streaming.service.ConsumerService;


public class ConsumerRunner {
    public static void main(String[] args) {

         ConsumerService consumerService= new ConsumerService();
         consumerService.consumeData();
    }
}
