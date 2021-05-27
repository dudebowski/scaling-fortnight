package com.kdb.clients.services;

import com.kdb.clients.service.ClientService;
import com.kdb.clients.service.IClientService;
import com.kdb.clients.service.apiclients.IExpenseClient;
import com.kdb.clients.service.dto.TotalExpenseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {


    @MockBean
    private IExpenseClient expenseClient;

    @Test
    public void getTotalExpensesByClientId() throws Exception {
        String json = "[{\"client_id\":20,\"date\":\"2021-05-27T16:18:34.627+00:00\",\"amount\":34.89},{\"client_id\":20,\"date\":\"2021-05-27T16:18:34.628+00:00\",\"amount\":134.17},{\"client_id\":20,\"date\":\"2021-05-27T16:18:34.628+00:00\",\"amount\":124.45},{\"client_id\":20,\"date\":\"2021-05-27T16:18:34.628+00:00\",\"amount\":88.83}]";

        when(expenseClient.GetExpensesJson(any(Long.class)))
                .thenReturn(json);

        IClientService service = new ClientService();
        TotalExpenseDTO total = service.GetTotalExpenses(20l);

        assertThat(total.getTotalAmount()).isEqualTo(new BigDecimal("382.34"));
    }
}