package com.sistemas_distribuidos.pratica2.net_wheels_hub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    private UUID id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;

    @NotBlank
    @NotNull
    @NotEmpty
    private String CPF;

    @NotBlank
    @NotNull
    @NotEmpty
    private String matricula;

    @NotBlank
    @NotNull
    @NotEmpty
    private Double salario;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carrosVendidos")
//    private List<Carro> carrosVendidos;

}
