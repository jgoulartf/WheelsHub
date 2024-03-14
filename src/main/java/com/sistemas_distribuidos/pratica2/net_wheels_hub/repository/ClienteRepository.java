package com.sistemas_distribuidos.pratica2.net_wheels_hub.repository;

import com.sistemas_distribuidos.pratica2.net_wheels_hub.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
