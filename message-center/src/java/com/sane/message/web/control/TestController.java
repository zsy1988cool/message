package com.sane.message.web.control;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

@RequestMapping(value="test")
@Controller
public class TestController {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value="test")
    public String test() {
        rabbitTemplate.convertAndSend("com.sane.fanout", "com.sane.school", "Hello you find me");
        return "index";
    }
}
