package com.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.Marca;
import com.escalab.service.IMarcaService;

@RestController
@RequestMapping("/marca")
@CrossOrigin("*")
public class MarcaController {

	@Autowired
	private IMarcaService service;
	
	@GetMapping()
	public ResponseEntity<List<Marca>> listar(){
		List<Marca> lista = service.listar();
		return new ResponseEntity<List<Marca>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Marca> listarPorId(@PathVariable("id") Integer id) {
		Marca mark = service.leerPorId(id);
		if (mark.getIdMarca() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Marca>(mark, HttpStatus.OK);
	}
	
	//nivel 2 (regresa una URI)
	@PostMapping()
	public ResponseEntity<Object> registrar(@Valid @RequestBody Marca marca) {
		Marca mark = service.registrar(marca);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getIdMarca()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Marca> modificar(@Valid @RequestBody Marca marca) {
		Marca mark = service.modificar(marca);
		return new ResponseEntity<Marca>(mark, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Marca mark = service.leerPorId(id);
		if (mark.getIdMarca() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
