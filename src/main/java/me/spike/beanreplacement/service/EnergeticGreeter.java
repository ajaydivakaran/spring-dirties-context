package me.spike.beanreplacement.service;

import me.spike.beanreplacement.contract.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
@Primary
public class EnergeticGreeter {

    public Message welcome() {
        return new Message("Hello Hello Hello");
    }
}
