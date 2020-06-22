package me.spike.beanreplacement;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

public class DestinationValueInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of("consumer.destination=" + UUID.randomUUID().toString()).applyTo(applicationContext);
    }
}
