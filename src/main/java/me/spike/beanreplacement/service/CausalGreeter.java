package me.spike.beanreplacement.service;

import me.spike.beanreplacement.contract.Message;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class CausalGreeter implements Greeter {

    @Override
    public Message welcome() {
        return new Message("Hello");
    }
}
