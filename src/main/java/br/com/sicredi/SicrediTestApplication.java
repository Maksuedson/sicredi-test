package br.com.sicredi;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import br.com.sicredi.entities.DadosContaRequestDTO;
import br.com.sicredi.entities.DadosContaResponseDTO;
import br.com.sicredi.services.ReceitaService;

@SpringBootApplication
public class SicrediTestApplication {

	public static void main(String[] args) throws RuntimeException, InterruptedException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		SpringApplication.run(SicrediTestApplication.class, args);
		
		ReceitaService rs = new ReceitaService();
		
		System.out.println("Parametro recebido: " +args[0]);
		
		Reader reader = Files.newBufferedReader(Paths.get(args[0]));
		
		CsvToBean<DadosContaRequestDTO> csvToBean = new CsvToBeanBuilder<DadosContaRequestDTO>(reader).withType(DadosContaRequestDTO.class).build();
		
		List<DadosContaRequestDTO> contasEnviadasParaOBanco = csvToBean.parse();
		
		Writer writer = Files.newBufferedWriter(Paths.get(args[0].replaceAll(".csv", "").concat("_response.csv")));
		
		StatefulBeanToCsv<DadosContaResponseDTO> beanToCsv = new StatefulBeanToCsvBuilder<DadosContaResponseDTO>(writer).build();
		
		List<DadosContaResponseDTO> contasRecebidasDoBanco = new ArrayList<DadosContaResponseDTO>();
		
		System.out.println("Processando dados...");
		
		for (DadosContaRequestDTO conta : contasEnviadasParaOBanco) {
			boolean contaAtualizada = rs.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getStatus());
			
			DadosContaResponseDTO contaResponse = new DadosContaResponseDTO(conta.getAgencia(), 
																	  conta.getConta(), 
																	  conta.getSaldo(), 
																	  conta.getStatus(),
																	  contaAtualizada ? true : false);
			
			contasRecebidasDoBanco.add(contaResponse);
		}
		
		beanToCsv.write(contasRecebidasDoBanco);
		
		writer.flush();
		writer.close();
		
		contasRecebidasDoBanco.forEach(c -> System.out.println(c));
		System.out.println("Processamento concluido.");
	}

}