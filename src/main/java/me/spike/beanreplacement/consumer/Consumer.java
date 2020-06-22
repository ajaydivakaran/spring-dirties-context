package me.spike.beanreplacement.consumer;

import lombok.AllArgsConstructor;
import me.spike.beanreplacement.service.EnergeticGreeter;
import me.spike.beanreplacement.service.MessageRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@AllArgsConstructor
@Service
public class Consumer {
    private EnergeticGreeter greeter;
    private MessageRepository repository;
    private ApplicationContext applicationContext;

    @JmsListener(destination = "foo.bar")
    public void consume(
            @Header(name = JmsHeaders.MESSAGE_ID, required = false) String messageId,
            TextMessage textMessage) {

        System.out.println("--- Consumed by context: " + applicationContext.toString());

        if ("Ahem hello!!".equals(greeter.welcome().getContent())) {
            repository.save();
        }
    }
}
