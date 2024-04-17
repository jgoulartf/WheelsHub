package com.sistemas_distribuidos.pratica2.net_wheels_hub.controller;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Cliente;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.ClienteService;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.ReplyDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ReplyDataService<Cliente> replyService = new ReplyDataService<>("/clientes");

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        ResponseEntity<Cliente> responseEntityNewCliente = service.createCliente(cliente);

        Cliente newCliente = responseEntityNewCliente.getBody();

        try {
            this.replyService.replyCreateData(newCliente);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseEntityNewCliente;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return service.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable UUID id, @RequestBody @Valid Cliente updatedCliente) {
        try {
            this.replyService.replyUpdateData(id, updatedCliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return service.updateCliente(id, updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable UUID id) {

        try {
            this.replyService.replyDeleteData(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return service.deleteClienteById(id);
    }
}