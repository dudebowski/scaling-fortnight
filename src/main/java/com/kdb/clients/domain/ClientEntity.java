package com.kdb.clients.domain;

public class ClientEntity {
    Long id;

    public Long getId() {
        return id;
    }

    public ClientEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}
