package me.spike.beanreplacement.consumer;

import lombok.AllArgsConstructor;
import me.spike.beanreplacement.service.Greeter;
import me.spike.beanreplacement.service.MessageRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@AllArgsConstructor
@Service
public class Consumer {
    private Greeter greeter;
    private MessageRepository repository;

    @JmsListener(destination = "foo.bar")
    public void consume(
            @Header(name = JmsHeaders.MESSAGE_ID, required = false) String messageId,
            TextMessage textMessage) {
        if ("Ahem hello!!".equals(greeter.welcome().getContent())) {
            repository.save();
        }
    }
}
