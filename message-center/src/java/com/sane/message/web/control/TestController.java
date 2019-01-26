package com.sane.message.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="test")
@Controller
public class TestController {

    @RequestMapping(value="test")
    public String test() {
        return "index";
    }
}
