package com.sane.message.core;

import com.sane.message.core.entity.MessageEntity;
import com.sane.message.core.event.EventHandler;

public class SaneMessageListener{
    public void onMessage(MessageEntity messageEntity) {
        EventHandler.handler(messageEntity.getEvent(), messageEntity.getMessage());
    }
}
