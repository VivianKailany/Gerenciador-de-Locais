package br.com.viviankailany.locais;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition
@SpringBootApplication
public class GerenciadorDeLocaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeLocaisApplication.class, args);
	}

}
