package br.com.sicredi.entities;

import lombok.Data;

@Data
public class DadosContaResponseDTO {

	private String agencia;
	private String conta;
	private Double saldo;
	private String status;
	private boolean enviado;

	public DadosContaResponseDTO(String agencia, String conta, Double saldo, String status, boolean enviado) {
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
		this.enviado = enviado;
	}
}
