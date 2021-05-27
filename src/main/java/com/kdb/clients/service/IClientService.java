package com.kdb.clients.service;

import com.kdb.clients.service.dto.ClientDTO;
import com.kdb.clients.service.dto.TotalExpenseDTO;

import java.math.BigDecimal;

public interface IClientService {

    Long  AddClient(String name);
    ClientDTO GetClient(Long id);
    TotalExpenseDTO GetTotalExpenses (Long clientId);

}
