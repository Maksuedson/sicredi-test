package br.com.sicredi.entities;

import lombok.Data;

@Data
public class DadosContaResponseDTO {

	private String agencia;
	private String conta;
	private Double saldo;
	private String status;
	private boolean enviado;

}
