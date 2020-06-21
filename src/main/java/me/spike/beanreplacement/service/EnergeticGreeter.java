package me.spike.beanreplacement.service;

import me.spike.beanreplacement.contract.Message;
import org.springframework.stereotype.Service;

@Service
public class EnergeticGreeter {

    public Message welcome() {
        return new Message("Hello Hello Hello");
    }
}
