package com.sistemas_distribuidos.pratica2.net_wheels_hub.controller;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.CarroService;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.CompraRequest;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.ReplyDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carros")
public class CarroController {
    private final CarroService service;

    @Autowired
    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) throws Exception {

        ReplyDataService<Carro> replyService = new ReplyDataService<Carro>();

        replyService.replyCreateData(carro);

        return service.createCarro(carro);
    }
    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){
        return service.getAllCarros();
    }

    @GetMapping("/quantidade")
    public ResponseEntity<String> getCarrosAmount(){
        return service.getCarrosAmount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateCarro (
            @PathVariable UUID id,
            @RequestBody @Valid Carro updatedCarro)
    {
        return service.updateCarro(id, updatedCarro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarroById(@PathVariable UUID id) {
        return service.deleteCarroById(id);
    }

    @PostMapping("/compraCarro/{id}")
    public ResponseEntity<String> compraCarroById(
            @PathVariable UUID id,
            @RequestBody @Valid CompraRequest compraRequest
    ) {
        return service.compraCarroById(id, compraRequest.getIdCliente(), compraRequest.getIdFuncionario());
    }

}

