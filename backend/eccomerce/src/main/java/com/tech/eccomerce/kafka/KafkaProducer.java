package com.tech.eccomerce.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.tech.eccomerce.dto.TransactionEvent;
import com.tech.eccomerce.dto.UserRegistrationEvent;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.transaction}")
    private String transactionTopic;

    @Value("${kafka.topic.user-registration}")
    private String userRegistrationTopic;

    public void sendTransactionEvent(TransactionEvent event) {
        try {
            logger.info("Publishing transaction event: {}", event.getTransactionId());
            Message<TransactionEvent> message = MessageBuilder
                    .withPayload(event)
                    .setHeader(KafkaHeaders.TOPIC, transactionTopic)
                    .setHeader("kafka_messageKey", String.valueOf(event.getSourceWalletId()))
                    .build();
            kafkaTemplate.send(message);
            logger.info("Transaction event published successfully: {}", event.getTransactionId());
        } catch (Exception e) {
            logger.error("Error publishing transaction event: {}", event.getTransactionId(), e);
            throw new RuntimeException("Failed to publish transaction event", e);
        }
    }

    public void sendUserRegistrationEvent(UserRegistrationEvent event) {
        try {
            logger.info("Publishing user registration event: {}", event.getUserId());
            Message<UserRegistrationEvent> message = MessageBuilder
                    .withPayload(event)
                    .setHeader(KafkaHeaders.TOPIC, userRegistrationTopic)
                    .setHeader("kafka_messageKey", String.valueOf(event.getUserId()))
                    .build();
            kafkaTemplate.send(message);
            logger.info("User registration event published successfully: {}", event.getUserId());
        } catch (Exception e) {
            logger.error("Error publishing user registration event: {}", event.getUserId(), e);
            throw new RuntimeException("Failed to publish user registration event", e);
        }
    }
}
