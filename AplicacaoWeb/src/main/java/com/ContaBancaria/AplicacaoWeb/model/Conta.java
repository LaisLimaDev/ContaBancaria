package com.ContaBancaria.AplicacaoWeb.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabela_conta")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome", length = 50)
	private String nome;
	
	@Column(name="cod_agencia", length = 4)
	private String codAgencia;
	
	@Column(name="num_conta", length = 8)
	private String numConta;

	@Column(name="digito_verificador", length = 3)
	private String digitoVerificador;
	
	@Column(name="id_registro", length = 20)
	private String IdRegistro;

	public Conta() {
		
	}


	public Conta(Long id, String nome, String codAgencia, String numConta, String digitoVerificador,
			String idRegistro) {
		this.id = id;
		this.nome = nome;
		this.codAgencia = codAgencia;
		this.numConta = numConta;
		this.digitoVerificador = digitoVerificador;
		IdRegistro = idRegistro;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	public String getIdRegistro() {
		return IdRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		IdRegistro = idRegistro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IdRegistro, digitoVerificador, id, nome, codAgencia, numConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(IdRegistro, other.IdRegistro)
				&& Objects.equals(digitoVerificador, other.digitoVerificador) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(codAgencia, other.codAgencia)
				&& Objects.equals(numConta, other.numConta);
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	
	
}
