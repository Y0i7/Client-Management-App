package com.Client.Managment.Controller;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.util.Collections;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: ClientController is a REST controller that handles HTTP requests related to clients.
 */
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCLients_empty() throws Exception {
        given(clientService.findAllClients()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clients").isEmpty());
    }

    @Test
    void postNewClient_conflict() throws Exception {
        Client c = new Client();
        c.setClientEmail("Joe@example.com");
        given(clientService.existByEmail(c.getClientEmail())).willReturn(true);

        mockMvc.perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isConflict());
    }
}
