package com.sane.message.core.connect;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import com.rabbitmq.client.Channel;
import java.util.concurrent.TimeoutException;

public abstract class Connector {

    protected Channel myChannel;
    protected Connection connection;
    protected String queueName;

    public Connector(String qName) throws IOException,TimeoutException {
        this.queueName = qName;

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connection = connectionFactory.newConnection();

        myChannel = connection.createChannel();
        myChannel.queueDeclare(queueName, false, false, false, null);
    }

    public void close() throws IOException,TimeoutException {
        this.myChannel.close();
        this.connection.close();
    }
}
