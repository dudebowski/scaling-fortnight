package com.kdb.clients.domain;

import com.kdb.clients.service.dto.ClientDTO;

import java.util.List;

public interface IRepository {
    ClientEntity GetClientById(Long clientID);
    Long AddClient(String name) ;
}
