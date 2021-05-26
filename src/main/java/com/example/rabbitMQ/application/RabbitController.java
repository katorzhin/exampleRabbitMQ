package com.example.rabbitMQ.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    Logger logger = LoggerFactory.getLogger(RabbitController.class);

    private final RabbitTemplate template;

    @Autowired
    public RabbitController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody String message) {
        logger.info("Emit to myQueue");
        template.setExchange("common exchange");
        template.convertAndSend(message);
        return ResponseEntity.ok("Success emit to queue");
    }
}