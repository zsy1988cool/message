package com.sane.message.core;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SaneMessageListener implements MessageListener {
    public void onMessage(Message var1) {
        System.out.print(var1.toString());
    }
}
