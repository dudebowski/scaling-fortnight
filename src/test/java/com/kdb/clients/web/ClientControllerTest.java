package com.kdb.clients.web;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.kdb.clients.service.IClientService;
import com.kdb.clients.service.dto.ClientDTO;
import com.kdb.clients.service.dto.TotalExpenseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc//(addFilters = false)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService service;

    @Test
    public void getClientShouldReturn200() throws Exception {
        ClientDTO client = new ClientDTO(20L, "libbie");
        when(service.GetClient(20L)).thenReturn(client);

        this.mockMvc.perform(get("/clients/20"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content().string(containsString("libbie")));
    }

    @Test
    public void getClientShouldReturn404() throws Exception {
        ClientDTO client = new ClientDTO(20L, "libbie");
        when(service.GetClient(30L)).thenReturn(client);

        this.mockMvc.perform(get("/clients/20"))
                .andDo(print())
                .andExpect(status()
                        .isNotFound());
    }

    @Test
    public void postClientShouldReturn201() throws Exception {
        when(service.AddClient(any(String.class))).thenReturn(2834L);
        String json = "{\"name\" : \"pietje bel\"}";
        this.mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status()
                        .isCreated())
                .andExpect(content().string(containsString("2834")));
    }

    @Test
    public void getTotalExpensesOK() throws Exception {
        TotalExpenseDTO total = new TotalExpenseDTO(20L,new BigDecimal("244.18"));
        when(service.GetTotalExpenses(20L)).thenReturn(total);
        this.mockMvc.perform(get("/clients/20/expenses/total"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content().string(containsString("244.18")));
    }
}
