package com.ContaBancaria.AplicacaoWeb.service.exception;

public class RecursoNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1l;
	
	public  RecursoNaoEncontradoException (String msg){
	super(msg);
	}
}
