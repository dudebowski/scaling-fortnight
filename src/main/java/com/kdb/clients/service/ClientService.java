package com.kdb.clients.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdb.clients.domain.ClientEntity;
import com.kdb.clients.domain.IRepository;
import com.kdb.clients.domain.Repository;
import com.kdb.clients.service.apiclients.Expense;
import com.kdb.clients.service.apiclients.ExpenseClient;
import com.kdb.clients.service.apiclients.IExpenseClient;
import com.kdb.clients.service.dto.ClientDTO;
import com.kdb.clients.service.dto.TotalExpenseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ClientService implements  IClientService{

    IRepository repo =  new Repository();

    @Override
    public Long AddClient(String name) {
        Long newId = repo.AddClient(name);
    return newId;
    }

    @Override
    public ClientDTO GetClient(Long id) {
        ClientEntity entity =  repo.GetClientById(id);
        if (entity==null) {
            return null;
        }
        return new ClientDTO(entity.getId(), entity.getName());
    }

    @Override
    public TotalExpenseDTO GetTotalExpenses(Long clientId)
    {
        IExpenseClient client = new ExpenseClient();
        String json= client.GetExpensesJson(clientId);
        List<Expense> expenses = ConvertToList(json);
        BigDecimal total = GetTotal(expenses);
        return new TotalExpenseDTO(clientId,total);
    }

    private List<Expense> ConvertToList(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Expense> asList = mapper.readValue(json, List.class);
            return asList;

        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    private BigDecimal GetTotal(List<Expense> expenses) {
        BigDecimal total=new BigDecimal("0");
        for (Object exp: expenses) {
            Double amount = (Double) ((LinkedHashMap)exp).get("amount");
            total = total.add(BigDecimal.valueOf(amount));
        }
        return total;
    }
}
