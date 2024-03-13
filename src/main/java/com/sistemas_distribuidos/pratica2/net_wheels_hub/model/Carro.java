package com.sistemas_distribuidos.pratica2.net_wheels_hub.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;

    @NotBlank
    @NotNull
    @NotEmpty
    private String renavam;

    @NotBlank
    @NotNull
    @NotEmpty
    private Integer anoFabricacao;

    @NotBlank
    @NotNull
    @NotEmpty
    private Integer quantidadeDisponivel;

    @NotBlank
    @NotNull
    @NotEmpty
    private Double preco;
}
