package me.spike.beanreplacement.controller;

import lombok.AllArgsConstructor;
import me.spike.beanreplacement.contract.Message;
import me.spike.beanreplacement.service.EnergeticGreeter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {
    private final EnergeticGreeter greeter;

    @GetMapping("/hello")
    public Message greet() {
        return greeter.welcome();
    }
}
