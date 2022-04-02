package com.ContaBancaria.AplicacaoWeb.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ContaBancaria.AplicacaoWeb.model.Conta;
import com.ContaBancaria.AplicacaoWeb.repository.ContaRepository;
import com.ContaBancaria.AplicacaoWeb.service.exception.ContaException;
import com.ContaBancaria.AplicacaoWeb.service.exception.RecursoNaoEncontradoException;

@Service
public class ContaService {

	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

//		Paginação

	public Page<Conta> findAll(Pageable pageable) {
		Page<Conta> list = contaRepository.findAll(pageable);

		return list;
	}

	public Conta getById(Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		Conta c = conta.orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
		return c;
	}

	public Conta create (Conta conta){
			
		try{
			Conta c = new Conta();
			c.setNome(conta.getNome());
			c.setCodAgencia(conta.getCodAgencia());
			c.setNumConta(conta.getNumConta());
			c.setDigitoVerificador(conta.getDigitoVerificador());
			c.setIdRegistro(conta.getIdRegistro());


			contaRepository.save(c);
			
			return c;
		} catch(DataIntegrityViolationException e) {
			throw new ContaException("Pessoa: "+ conta.getNome()+" já existe na base da dados");
		}		
	}
	
	@Transactional
	public Conta update(Conta pessoa, Long id) {		
		try {
			Optional<Conta> p = contaRepository.findById(id);
			p.get().setNome(pessoa.getNome());
			p.get().setCodAgencia(pessoa.getCodAgencia());
			p.get().setNumConta(pessoa.getNumConta());
			p.get().setDigitoVerificador(pessoa.getDigitoVerificador());
			p.get().setIdRegistro(pessoa.getIdRegistro());




			contaRepository.save(p.get());			
			return p.get();			
		}catch(EntityNotFoundException e ) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}catch(NoSuchElementException e ) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {			 
			contaRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}catch(DataIntegrityViolationException e) {
			throw new ContaException("Integridade violada!");
		}
	}
	
}