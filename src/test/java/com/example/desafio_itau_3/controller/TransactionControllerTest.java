package com.example.desafio_itau_3.controller;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateTransaction() throws Exception {
        String body = """
        {
        "valor": 10,
        "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated());
    }

    @Test
    void shouldRejectFutureTransaction() throws Exception {
        String body = """
        {
        "valor": 10,
        "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now().plusSeconds(10));

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldRejectNegativeValue() throws Exception {
        String body = """
        {
        "valor": -10,
        "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnZeroStatisticsWhenEmpty() throws Exception {
        mockMvc.perform(get("/estatistica"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.count").value(0))
            .andExpect(jsonPath("$.sum").value(0.0))
            .andExpect(jsonPath("$.avg").value(0.0))
            .andExpect(jsonPath("$.min").value(0.0))
            .andExpect(jsonPath("$.max").value(0.0));
    }


}
