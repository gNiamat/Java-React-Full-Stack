package com.tech.eccomerce.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tech.eccomerce.dto.TransactionEvent;
import com.tech.eccomerce.dto.UserRegistrationEvent;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(
            topics = "${kafka.topic.transaction}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "transactionKafkaListenerContainerFactory"
    )
    public void consumeTransactionEvent(TransactionEvent event) {
        try {
            logger.info("Consuming transaction event: {}", event.getTransactionId());
            logger.info("Transaction Type: {}, Amount: {}, Status: {}", 
                    event.getType(), event.getAmount(), event.getStatus());
            
            // Process transaction event - log, send notifications, etc.
            processTransactionEvent(event);
            
            logger.info("Transaction event processed successfully: {}", event.getTransactionId());
        } catch (Exception e) {
            logger.error("Error processing transaction event: {}", event.getTransactionId(), e);
        }
    }

    @KafkaListener(
            topics = "${kafka.topic.user-registration}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "userRegistrationKafkaListenerContainerFactory"
    )
    public void consumeUserRegistrationEvent(UserRegistrationEvent event) {
        try {
            logger.info("Consuming user registration event: {}", event.getUserId());
            logger.info("User: {}, Email: {}, Wallet ID: {}", 
                    event.getName(), event.getEmail(), event.getWalletId());
            
            // Process user registration event - send welcome email, etc.
            processUserRegistrationEvent(event);
            
            logger.info("User registration event processed successfully: {}", event.getUserId());
        } catch (Exception e) {
            logger.error("Error processing user registration event: {}", event.getUserId(), e);
        }
    }

    private void processTransactionEvent(TransactionEvent event) {
        // Implement business logic for transaction events
        // Examples:
        // - Send transaction confirmation email
        // - Update analytics/reporting systems
        // - Trigger notifications
        // - Audit logging
        logger.debug("Processing transaction event with ID: {} of type: {}", 
                event.getTransactionId(), event.getType());
    }

    private void processUserRegistrationEvent(UserRegistrationEvent event) {
        // Implement business logic for user registration events
        // Examples:
        // - Send welcome email
        // - Initialize user preferences
        // - Send introductory offers
        // - Trigger onboarding workflow
        logger.debug("Processing user registration event for user: {} with email: {}", 
                event.getUserId(), event.getEmail());
    }
}
