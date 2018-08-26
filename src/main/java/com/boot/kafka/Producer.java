package com.boot.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Log logger = LogFactory.getLog(Producer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${customer.kafka.topic.test}")
    private String topic;

    public void send(String message){
        logger.info("sending message='"+message+"' to topic='"+topic+"'");
        kafkaTemplate.send(topic, message);
    }
}
