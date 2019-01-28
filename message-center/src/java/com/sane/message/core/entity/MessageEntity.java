package com.sane.message.core.entity;

import com.sane.message.core.event.Event;

import java.io.Serializable;

public class MessageEntity implements Serializable {

    private Event event;
    private String message;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
