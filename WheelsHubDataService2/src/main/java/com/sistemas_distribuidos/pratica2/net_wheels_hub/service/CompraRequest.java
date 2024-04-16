package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import java.util.UUID;

public class CompraRequest {
    private UUID idCliente;
    private UUID idFuncionario;

    // Getters e setters

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}