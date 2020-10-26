package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Vendedor;

public interface IVendedorRepo extends JpaRepository<Vendedor, Integer> {

}
