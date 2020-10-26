package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Marca;
import com.escalab.repo.IMarcaRepo;
import com.escalab.service.IMarcaService;

@Service
public class MarcaServiceImpl implements IMarcaService{

	@Autowired
	private IMarcaRepo repo;
	
	@Override
	public Marca registrar(Marca mar) {
		return repo.save(mar);
	}
	
	@Override
	public Marca modificar(Marca mar) {
		return repo.save(mar);
	}
	
	@Override
	public List<Marca> listar() {
		return repo.findAll();
	}
	
	@Override
	public Marca leerPorId(Integer id) {
		Optional<Marca> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Marca();
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
