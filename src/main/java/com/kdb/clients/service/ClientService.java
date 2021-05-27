package com.kdb.clients.service;

import com.kdb.clients.domain.ClientEntity;
import com.kdb.clients.domain.IRepository;
import com.kdb.clients.domain.Repository;
import com.kdb.clients.service.dto.ClientDTO;
import com.kdb.clients.service.dto.TotalExpenseDTO;
import org.springframework.stereotype.Service;

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
    public TotalExpenseDTO GetTotalExpenses(Long clientId) {
        return null;
    }
}
