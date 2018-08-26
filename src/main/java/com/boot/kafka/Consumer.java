package com.boot.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private static final Log logger = LogFactory.getLog(Consumer.class);
    @KafkaListener(topics = "${customer.kafka.topic.test}")
    public void listen(@Payload String message) {
        logger.info("received message='"+message+"'");
    }
}
