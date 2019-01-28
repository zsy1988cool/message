package com.sane.message.web.service;

import com.sane.message.core.entity.MessageEntity;
import com.sane.message.core.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    QueueConsumer consumer;

    public MessageService() throws Exception{
        try {
            consumer = new QueueConsumer("queue001");
        } catch (Exception e) {
            e.printStackTrace();
            consumer = null;
        }

    }

    public QueueConsumer getConsumer() {
        return consumer;
    }

    public void sendMessage(Event event, String message) throws Exception {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setEvent(event);
        messageEntity.setMessage(message);

        Producer producer = new Producer("queue001");
        producer.sendMessage(messageEntity);
        producer.close();
    }
}
