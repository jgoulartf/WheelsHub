package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Cliente;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Funcionario;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.ClienteRepository;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {
    FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public ResponseEntity<Funcionario> createFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.FOUND);
    }

    public ResponseEntity<Funcionario> getById(UUID id) {
        if (funcionarioRepository.existsById(id))
            return new ResponseEntity<>(funcionarioRepository.findById(id).get(), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<Funcionario> updateFuncionario(UUID id, Funcionario updatedFuncionario) {
        if (funcionarioRepository.existsById(id)) {
            updatedFuncionario.setId(id);
            funcionarioRepository.save(updatedFuncionario);
            return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteFuncionarioById(UUID id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return new ResponseEntity<>("Funcionario deletado com sucesso", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado");
        }

    }
}
