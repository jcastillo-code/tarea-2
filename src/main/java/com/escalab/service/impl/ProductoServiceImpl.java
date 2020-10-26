package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Producto;
import com.escalab.repo.IProductoRepo;
import com.escalab.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepo repo;
	
	@Override
	public Producto registrar(Producto prod) {
		return repo.save(prod);
	}
	
	@Override
	public Producto modificar(Producto prod) {
		return repo.save(prod);
	}
	
	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}
	
	@Override
	public Producto leerPorId(Integer id) {
		Optional<Producto> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Producto();
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
