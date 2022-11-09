package br.com.sicredi.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class DadosContaRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agencia;
	private String conta;
	private Double saldo;
	private String status;

}
