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
import com.escalab.model.Vendedor;
import com.escalab.service.IVendedorService;

@RestController
@RequestMapping("/vendedor")
@CrossOrigin("*")
public class VendedorController {
	
	@Autowired
	private IVendedorService service;
	
	@GetMapping()
	public ResponseEntity<List<Vendedor>> listar(){
		List<Vendedor> lista = service.listar();
		return new ResponseEntity<List<Vendedor>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vendedor> listarPorId(@PathVariable("id") Integer id) {
		Vendedor vended = service.leerPorId(id);
		if (vended.getIdVendedor() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Vendedor>(vended, HttpStatus.OK);
	}
	
	//nivel 2 (regresa una URI)
	@PostMapping()
	public ResponseEntity<Object> registrar(@Valid @RequestBody Vendedor vendedor) {
		Vendedor vended = service.registrar(vendedor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vendedor.getIdVendedor()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Vendedor> modificar(@Valid @RequestBody Vendedor vendedor) {
		Vendedor vended = service.modificar(vendedor);
		return new ResponseEntity<Vendedor>(vended, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Vendedor vended = service.leerPorId(id);
		if (vended.getIdVendedor() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
