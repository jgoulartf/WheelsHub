package com.sistemas_distribuidos.pratica2.net_wheels_hub.controller;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.service.CarroService;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.dto.CompraCarroDTO;
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
    private ReplyDataService<Carro> replyCRUDService = new ReplyDataService<>("/carros");
    private ReplyDataService<CompraCarroDTO> replyBuyCarService = new ReplyDataService<>("/carros");

    @Autowired
    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) throws Exception {

        ResponseEntity<Carro> responseEntityNewCarro = service.createCarro(carro);
        Carro newCarro = responseEntityNewCarro.getBody();

        try {
            this.replyCRUDService.replyCreateData(newCarro);
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return responseEntityNewCarro;
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
        try {
            this.replyCRUDService.replyUpdateData(id, updatedCarro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return service.updateCarro(id, updatedCarro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarroById(@PathVariable UUID id) {

        try {
            this.replyCRUDService.replyDeleteData(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return service.deleteCarroById(id);
    }


    // TODO: Testar após fazer o reply das outras entidades funcionário
    @PostMapping("/compraCarro/{id}")
    public ResponseEntity<String> compraCarroById(
            @PathVariable UUID id,
            @RequestBody @Valid CompraCarroDTO compraRequest
    ) {
        System.out.println("para");
        ResponseEntity<String> responseEntityCompraCarro = service.compraCarroById(id, compraRequest.getIdCliente(), compraRequest.getIdFuncionario());

        try {
            this.replyBuyCarService.replyBuyCarData(id, compraRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseEntityCompraCarro;
    }
}

