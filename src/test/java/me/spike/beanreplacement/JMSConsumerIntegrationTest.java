package me.spike.beanreplacement;

import me.spike.beanreplacement.contract.Message;
import me.spike.beanreplacement.service.EnergeticGreeter;
import me.spike.beanreplacement.service.MessageRepository;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DestinationValueInitializer.class)
public class JMSConsumerIntegrationTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${consumer.destination}")
    private String destination;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private EnergeticGreeter greeter;

    @MockBean
    private MessageRepository repository;

    //Todo - To get all tests in this project to pass when entire test suite is run look at Todos added.
    @Test
    public void shouldInvokeRepositoryWhenGreetedWithASpecificMessage() {
        when(greeter.welcome()).thenReturn(new Message("Ahem hello!!"));

        System.out.println("--- Send from context: " + applicationContext.toString());

        jmsTemplate.send(destination, session -> session.createTextMessage("hello world"));

        Awaitility.await().atMost(10, TimeUnit.SECONDS).untilAsserted(
                () -> verify(repository, times(1)).save()
        );
    }
}
