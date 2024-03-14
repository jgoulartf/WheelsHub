package com.sistemas_distribuidos.pratica2.net_wheels_hub.service;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.CarroRepository;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.ClienteRepository;
import com.sistemas_distribuidos.pratica2.net_wheels_hub.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public CarroService(CarroRepository repository, ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository) {
        this.carroRepository = repository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public ResponseEntity<Carro> createCarro(Carro carro) {
        carroRepository.save(carro);
        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Carro>> getAllCarros() {
        List<Carro> carros = carroRepository.findAll();
        return new ResponseEntity<>(carros, HttpStatus.FOUND);
    }

    public ResponseEntity<Carro> getById(UUID id) {
        if (carroRepository.existsById(id))
            return new ResponseEntity<>(carroRepository.findById(id).get(), HttpStatus.OK);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<Carro> updateCarro(UUID id, Carro updatedCarro) {
        if (carroRepository.existsById(id)) {
            updatedCarro.setId(id);
            carroRepository.save(updatedCarro);
            return new ResponseEntity<>(updatedCarro, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteCarroById(UUID id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return new ResponseEntity<>("Carro deletado com sucesso", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado");
        }

    }

    @Transactional
    public ResponseEntity<String> compraCarroById(UUID idCarro) {
        if (carroRepository.existsById(idCarro)) {
            // Carro vendido
            Optional<Carro> carroVendido = carroRepository.findById(idCarro);

            carroVendido.ifPresent(carro -> {
                if (carro.getQuantidadeDisponivel() > 0) {
                    carro.setId(idCarro);
                    carro.setQuantidadeDisponivel(carro.getQuantidadeDisponivel() - 1);
                    carroRepository.save(carro);

                    // Funcionario vende carro
//                    funcionarioRepository.findById(idFuncionario)
//                            .ifPresent(funcionario -> {
//                                List<Carro> carrosVendidos = funcionario.getCarrosVendidos();
//                                carrosVendidos.add(carro);
//                                funcionario.setCarrosVendidos(carrosVendidos);
//                                funcionarioRepository.save(funcionario);
//                            });
//
//                    // Cliente compra carro
//                    clienteRepository.findById(idCliente)
//                            .ifPresent(cliente -> {
//                                List<Carro> carrosComprados = cliente.getCarrosComprados();
//                                carrosComprados.add(carro);
//                                cliente.setCarrosComprados(carrosComprados);
//                                clienteRepository.save(cliente);
//                            });
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não disponível em estoque!");
                }
            });

            return new ResponseEntity<>("Carro vendido com sucesso", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado");
        }
    }
}



