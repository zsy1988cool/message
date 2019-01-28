package com.sane.message.web.control;

import com.sane.message.core.event.Event;
import com.sane.message.web.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="test")
@Controller
public class MessageController {
/*
    @Autowired
    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }*/

    @Autowired
    MessageService messageService;

    @RequestMapping(value="listen")
    public String listen() throws Exception {
        if(messageService.getConsumer() != null) {
            new Thread(messageService.getConsumer()).start();
        }
        return "index";
    }

    @RequestMapping(value="send")
    public String send() throws Exception{
        //rabbitTemplate.convertAndSend("com.exchanged.fanout", "com.sane.school", "Hello you find me");
        messageService.sendMessage(Event.Event1, "Hello World");
        return "index";
    }
}
