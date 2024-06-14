package br.com.alura.desafio_tabela_fipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.desafio_tabela_fipe.application.Application;

@SpringBootApplication
public class DesafioTabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Application.run();
	}
}
