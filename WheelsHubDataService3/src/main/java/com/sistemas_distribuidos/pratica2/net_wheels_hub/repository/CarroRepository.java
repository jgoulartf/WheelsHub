package com.sistemas_distribuidos.pratica2.net_wheels_hub.repository;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

    Optional<Carro> findById(UUID uuid);
    List<Carro> findAll();
    //Optional<Carro> findByNome(String nome);
    //Optional<Carro> findByRenavam(String renavam);

}
