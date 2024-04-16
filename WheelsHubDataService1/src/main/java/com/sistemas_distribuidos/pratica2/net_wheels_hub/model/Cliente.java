package com.sistemas_distribuidos.pratica2.net_wheels_hub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UUID id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;

    @NotBlank
    @NotNull
    @NotEmpty
    private String CPF;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Carro> carrosComprados;

}
