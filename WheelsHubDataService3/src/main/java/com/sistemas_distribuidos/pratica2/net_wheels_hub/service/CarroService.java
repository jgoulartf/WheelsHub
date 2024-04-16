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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    public ResponseEntity<String> getCarrosAmount() {
        List<Carro> carros = carroRepository.findAll();
        Integer qtdCarros = carros.size();
        return new ResponseEntity<String>("A loja tem " + qtdCarros + " carros em estoque.", HttpStatus.OK);
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
    public ResponseEntity<String> compraCarroById(UUID idCarro, UUID idCliente, UUID idFuncionario) {
        if (carroRepository.existsById(idCarro)) {
            // Carro vendido
            Optional<Carro> carroVendido = carroRepository.findById(idCarro);

            AtomicReference<String> vendido = new AtomicReference<>("");
            AtomicReference<String> vendedor = new AtomicReference<>("");
            AtomicReference<String> comprador = new AtomicReference<>("");
            AtomicInteger quantidadeCarroDisponivel = new AtomicInteger();
            carroVendido.ifPresent(carro -> {
                if (carro.getQuantidadeDisponivel() > 0) {
                    carro.setId(idCarro);
                    carro.setQuantidadeDisponivel(carro.getQuantidadeDisponivel() - 1);
                    quantidadeCarroDisponivel.set(carro.getQuantidadeDisponivel());
                    carroRepository.save(carro);
                    vendido.set(carro.getNome());
                    // Funcionario vende carro
                    funcionarioRepository.findById(idFuncionario)
                            .ifPresent(funcionario -> {
                                vendedor.set(funcionario.getNome());
                            });


                    // Cliente compra carro
                    clienteRepository.findById(idCliente)
                            .ifPresent(cliente -> {
                                comprador.set(cliente.getNome());
                            });
                }
            });
            if(quantidadeCarroDisponivel.get() < 1){
                return new
                        ResponseEntity<>("Carro não está disponível em estoque!", HttpStatus.NOT_FOUND);
            }
            return new
                    ResponseEntity<>("Carro " + vendido + " vendido com sucesso pelo funcionario " + vendedor + " para o cliente "+ comprador, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado");
        }
    }
}



