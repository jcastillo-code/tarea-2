package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Marca;

public interface IMarcaRepo extends JpaRepository<Marca, Integer> {

}
