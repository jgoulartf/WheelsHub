package com.sistemas_distribuidos.pratica2.net_wheels_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public abstract class Car {

    @Id
    private UUID id;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
