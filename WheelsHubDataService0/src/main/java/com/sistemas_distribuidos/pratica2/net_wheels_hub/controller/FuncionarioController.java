package com.sistemas_distribuidos.pratica2.net_wheels_hub.controller;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Funcionario;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.FuncionarioService;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.ReplyDataService;
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

    private final ReplyDataService<Funcionario> replyService = new ReplyDataService<>("/funcionarios");

    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {

        ResponseEntity<Funcionario> responseEntityNewFuncionario = service.createFuncionario(funcionario);
        Funcionario newFuncionario = responseEntityNewFuncionario.getBody();

        try {
            this.replyService.replyCreateData(newFuncionario);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseEntityNewFuncionario;
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
    public ResponseEntity<Funcionario> updateFuncionario(
            @PathVariable UUID id,
            @RequestBody @Valid Funcionario updatedFuncionario)
    {
        ResponseEntity<Funcionario> responseEntityFuncionario = service.updateFuncionario(id, updatedFuncionario);

        try {
            this.replyService.replyUpdateData(id, updatedFuncionario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseEntityFuncionario;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFuncionarioById(@PathVariable UUID id) {
        try {
            this.replyService.replyDeleteData(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return service.deleteFuncionarioById(id);
    }

}
