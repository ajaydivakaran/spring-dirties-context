package me.spike.beanreplacement.controller;

import me.spike.beanreplacement.contract.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Message greet() {
        return new Message("hello");
    }
}
