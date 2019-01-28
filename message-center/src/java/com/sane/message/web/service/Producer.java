package com.sane.message.web.service;

import com.sane.message.core.connect.Connector;
import com.sane.message.core.entity.MessageEntity;
import org.springframework.util.SerializationUtils;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer extends Connector {

    public Producer(String quequeName) throws IOException,TimeoutException {
        super(quequeName);
    }

    public void sendMessage(MessageEntity object) throws IOException {
        myChannel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }

    @PreDestroy
    public void destory() {
        try {
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
