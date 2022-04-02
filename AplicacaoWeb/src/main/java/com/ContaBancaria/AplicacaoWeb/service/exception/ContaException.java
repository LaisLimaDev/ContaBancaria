package com.ContaBancaria.AplicacaoWeb.service.exception;

public class ContaException extends RuntimeException{
	private static final long serialVersionUID = 1l;
	
	public  ContaException (String msg){
	super(msg);
	}
}
