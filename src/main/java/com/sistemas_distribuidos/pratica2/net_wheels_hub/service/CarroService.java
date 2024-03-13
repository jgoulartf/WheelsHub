package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.CarroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CarroService {

    private CarroRepository repository;

    @Autowired
    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<Carro> createCarro(Carro carro) {
        repository.save(carro);
        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Carro>> getAllCarros() {
        List<Carro> carros = repository.findAll();
        return new ResponseEntity<>(carros, HttpStatus.FOUND);
    }

    public ResponseEntity<Carro> getById(UUID id) {
        if(repository.existsById(id))
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<Carro> updateCarro(UUID id, Carro updatedCarro) {
        if (repository.existsById(id)) {
            updatedCarro.setId(id);
            repository.save(updatedCarro);
            return new ResponseEntity<>(updatedCarro, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteCarroById(UUID id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>("Carro deletado com sucesso", HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado");
        }

    }




}
