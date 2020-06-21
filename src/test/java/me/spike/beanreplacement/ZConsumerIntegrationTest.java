package me.spike.beanreplacement;

import me.spike.beanreplacement.contract.Message;
import me.spike.beanreplacement.service.EnergeticGreeter;
import me.spike.beanreplacement.service.MessageRepository;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ZConsumerIntegrationTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @MockBean
    private EnergeticGreeter greeter;

    @MockBean
    private MessageRepository repository;

    //Todo - To get all tests in this project to pass look at Todo added to FirstHappyPathApplicationTest
    @Test
    public void shouldReturnModifiedGreeting() {
        when(greeter.welcome()).thenReturn(new Message("Ahem hello!!"));

        jmsTemplate.send("foo.bar", session -> session.createTextMessage("hello world"));

        Awaitility.await().atMost(10, TimeUnit.SECONDS).untilAsserted(
                () -> verify(repository, times(1)).save()
        );
    }
}
