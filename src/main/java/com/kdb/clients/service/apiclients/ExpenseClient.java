package com.kdb.clients.service.apiclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpenseClient implements IExpenseClient {

    final static String url = "http://localhost:8091/expenses/client";

    @Override
    public String  GetExpensesJson(Long clientId) {
        final String uri = String.format("%s/%d",url,clientId);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }
}
