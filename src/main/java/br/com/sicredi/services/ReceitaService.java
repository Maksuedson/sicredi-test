package br.com.sicredi.services;

import java.util.ArrayList;
import java.util.List;

public class ReceitaService {

	// Esta e a implementação interna do "servico" do banco central. Veja o codigo fonte abaixo para ver os formatos esperados pelo Banco Central neste cenario.

	public boolean atualizarConta(String agencia, String conta, double saldo, String status) throws RuntimeException, InterruptedException {
        // Formato agencia: 0000
        if (agencia == null || agencia.length() != 4) {
            return false;
        }
        
        // Formato conta: 000000
        if (conta == null || conta.length() != 6) {
            return false;
        }
        
        // Tipos de status validos:
        List<String> tipos = new ArrayList<String>();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");                
                
        if (status == null || !tipos.contains(status)) {
            return false;
        }

        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 5000) + 1000;
        Thread.sleep(wait);

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) {
            throw new RuntimeException("Error");
        }

        return true;
    }
}
