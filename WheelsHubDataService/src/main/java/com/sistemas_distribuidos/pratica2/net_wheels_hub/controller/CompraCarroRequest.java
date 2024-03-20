package com.sistemas_distribuidos.pratica2.net_wheels_hub.controller;

import java.util.UUID;

public class CompraCarroRequest {
    private UUID idCliente;
    private UUID idFuncionario;

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
