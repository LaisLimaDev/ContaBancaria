package com.ContaBancaria.AplicacaoWeb.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ContaBancaria.AplicacaoWeb.model.Conta;
import com.ContaBancaria.AplicacaoWeb.service.ContaService;

@RestController
@RequestMapping("ContaBancaria/contas")

public class ContaController {

	private final ContaService contaService;

	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

	@GetMapping

	public ResponseEntity<Page<Conta>> getAll(Pageable pageable) {
		Page<Conta> listaConta = contaService.findAll(pageable);

		return ResponseEntity.ok().body(listaConta);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Conta> getById(@PathVariable Long id) {
		Conta conta = contaService.getById(id);
		return ResponseEntity.ok(conta);
	}

	@PostMapping
	public ResponseEntity<Conta> create(@RequestBody Conta conta){
		Conta c = contaService.create(conta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(c.getId()).toUri();	
		
		return ResponseEntity.created(location).body(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Conta> update(@RequestBody Conta conta, @PathVariable Long id){
		conta = contaService.update(conta, id);
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(conta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Conta> delete(@PathVariable Long id){
		contaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}