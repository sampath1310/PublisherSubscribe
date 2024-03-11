package org.streaming;

import org.streaming.service.ProducerService;

public class Main {
    public static void main(String[] args) {

        ProducerService producerService = new ProducerService();
        producerService.produceData();
    }
}