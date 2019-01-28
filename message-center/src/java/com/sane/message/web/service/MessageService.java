package com.sane.message.web.service;

import com.sane.message.core.entity.MessageEntity;
import com.sane.message.core.event.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    QueueConsumer consumer;

    public MessageService() throws Exception{
        try {
            consumer = new QueueConsumer("message.queue.comm", rabbitTemplate);
        } catch (Exception e) {
            e.printStackTrace();
            consumer = null;
        }
    }

    public QueueConsumer getConsumer() {
        if(consumer != null && consumer.getRabbitTemplate() == null) {
            consumer.setRabbitTemplate(rabbitTemplate);
        }
        return consumer;
    }

    public void sendMessage(Event event, String message) throws Exception {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setEvent(event);
        messageEntity.setMessage(message);

        rabbitTemplate.convertAndSend("queueTestKey", messageEntity);
    }
}
