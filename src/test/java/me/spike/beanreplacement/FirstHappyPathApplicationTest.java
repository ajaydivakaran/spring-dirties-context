package me.spike.beanreplacement;

import me.spike.beanreplacement.contract.Message;
import me.spike.beanreplacement.service.EnergeticGreeter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FirstHappyPathApplicationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EnergeticGreeter greeter;

    @Test
    void shouldReturnModifiedGreeting() throws Exception {
        when(greeter.welcome()).thenReturn(new Message("Ahem hello!!"));

        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"content\":\"Ahem hello!!\"}")));
    }
}
