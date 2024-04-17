package com.sistemas_distribuidos.pratica2.net_wheels_hub.dto;

import java.util.UUID;

public class CompraCarroDTO {
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
