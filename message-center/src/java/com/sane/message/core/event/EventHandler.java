package com.sane.message.core.event;

public class EventHandler {
    static public void handler(Event event, Object  message) {
        switch (event) {
            case Event1:
                System.out.println("publish 1 event" + message);
                break;
            case Event2:
                System.out.println("publish 2 evnet" + message);
                break;
        }
    }
}
