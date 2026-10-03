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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.transaction}")
    private String transactionTopic;

    @Value("${kafka.topic.user-registration}")
    private String userRegistrationTopic;

    @Retry(name = "kafkaPublisher", fallbackMethod = "publishTransactionEventFallback")
    @CircuitBreaker(name = "kafkaPublisher", fallbackMethod = "publishTransactionEventFallback")
    public void sendTransactionEvent(TransactionEvent event) {
        logger.info("Publishing transaction event: {}", event.getTransactionId());
        Message<TransactionEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, transactionTopic)
                .setHeader("kafka_messageKey", String.valueOf(event.getSourceWalletId()))
                .build();
        kafkaTemplate.send(message);
        logger.info("Transaction event published successfully: {}", event.getTransactionId());
    }

    @Retry(name = "kafkaPublisher", fallbackMethod = "publishUserRegistrationEventFallback")
    @CircuitBreaker(name = "kafkaPublisher", fallbackMethod = "publishUserRegistrationEventFallback")
    public void sendUserRegistrationEvent(UserRegistrationEvent event) {
        logger.info("Publishing user registration event: {}", event.getUserId());
        Message<UserRegistrationEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, userRegistrationTopic)
                .setHeader("kafka_messageKey", String.valueOf(event.getUserId()))
                .build();
        kafkaTemplate.send(message);
        logger.info("User registration event published successfully: {}", event.getUserId());
    }

    public void publishTransactionEventFallback(TransactionEvent event, Exception e) {
        logger.warn("Circuit breaker triggered for transaction event {}. Kafka is unavailable. Event dropped. Reason: {}",
                event.getTransactionId(), e.getMessage());
    }

    public void publishUserRegistrationEventFallback(UserRegistrationEvent event, Exception e) {
        logger.warn("Circuit breaker triggered for user registration event {}. Kafka is unavailable. Event dropped. Reason: {}",
                event.getUserId(), e.getMessage());
    }
}
