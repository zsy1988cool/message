package com.sane.message.web.service;

import com.rabbitmq.client.*;
import com.sane.message.core.connect.Connector;
import com.sane.message.core.entity.MessageEntity;
import com.sane.message.core.event.EventHandler;

import org.springframework.amqp.utils.SerializationUtils;

import javax.annotation.PreDestroy;
import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class QueueConsumer extends Connector implements Runnable, Consumer{
    public QueueConsumer(String queueName) throws IOException, TimeoutException {
        super(queueName);
    }

    @Override
    public void run() {
        try {
            myChannel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String s) {

    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
        MessageEntity entity = (MessageEntity) SerializationUtils.deserialize(body);
        EventHandler.handler(entity.getEvent(), entity.getMessage());
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
