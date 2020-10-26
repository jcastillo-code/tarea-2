package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Integer> {

}
