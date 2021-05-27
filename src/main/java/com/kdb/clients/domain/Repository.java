package com.kdb.clients.domain;

import com.kdb.clients.service.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Repository implements  IRepository {

    @Override
    public ClientEntity GetClientById(Long clientID) {
        ClientEntity entity = data.stream()
                .filter(client -> clientID.equals(client.getId()))
                .findFirst()
                .orElse(null);
        return  entity;
    }

    @Override
    public Long AddClient(String name) {
        Function<ClientEntity, Long> totalMapper = client -> client.getId();
        Long lastId = data.stream()
                .map(totalMapper)
                .reduce(0L, Long::max);
        data.add(new ClientEntity(lastId++,name));
        return lastId++;
    }

    private static List<ClientEntity> data = new ArrayList() {{
            add(new ClientEntity(44L, "Pietje Puk" ));
            add(new ClientEntity(45L, "pietje puk" ));
            add(new ClientEntity(46L, "pietje puk" ));
            add(new ClientEntity(47L, "pietje puk" ));
            add(new ClientEntity(48L, "pietje puk" ));
        }};


}
