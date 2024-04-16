package com.sistemas_distribuidos.pratica2.net_wheels_hub.controller;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Cliente;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Funcionario;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.ClienteService;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        return service.createFuncionario(funcionario);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        return service.getAllFuncionarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateCliente(
            @PathVariable UUID id,
            @RequestBody @Valid Funcionario updatedFuncionario)
    {
        return service.updateFuncionario(id, updatedFuncionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable UUID id) {
        return service.deleteFuncionarioById(id);
    }

}
