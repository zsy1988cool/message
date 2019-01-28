package com.sane.message.web.service;

import com.sane.message.core.entity.MessageEntity;
import com.sane.message.core.event.EventHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class QueueConsumer implements Runnable {
    private String queueName;
    private RabbitTemplate rabbitTemplate;

    public QueueConsumer(String queueName, RabbitTemplate rabbitTemplate) {
        this.queueName = queueName;
        this.rabbitTemplate = rabbitTemplate;
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run() {
        MessageEntity messageEntity = (MessageEntity)rabbitTemplate.receiveAndConvert(this.queueName, 100000);
        if(messageEntity != null) {
            EventHandler.handler(messageEntity.getEvent(), messageEntity.getMessage());
        }
    }
}
