package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Cliente;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.CarroRepository;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ResponseEntity<Cliente> createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.FOUND);
    }

    public ResponseEntity<Cliente> getById(UUID id) {
        if (clienteRepository.existsById(id))
            return new ResponseEntity<>(clienteRepository.findById(id).get(), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<Cliente> updateCliente(UUID id, Cliente updatedCliente) {
        if (clienteRepository.existsById(id)) {
            updatedCliente.setId(id);
            clienteRepository.save(updatedCliente);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteClienteById(UUID id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

    }


}
