package me.spike.beanreplacement.controller;

import me.spike.beanreplacement.contract.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {

    @GetMapping("/bye")
    public Message bye() {
        return new Message("bye");
    }
}
