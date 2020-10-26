package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Integer> {

}
