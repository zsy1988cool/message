package com.sane.message.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="websocket")
@Controller
public class MessageWSController {

    @RequestMapping(value = "show")
    String showWebsocket() {
        return "index";
    }
}
