package br.inatel.labs.labrest_client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest_client.model.Curso;

public class WebClientPutCurso
{
	public static void main(String [] args)
	{
		Curso cursoJPA = new Curso(1L, "Curso Java JPA", 85);
		
		ResponseEntity<Void> responseEntity = WebClient.create("http://localhost:8080")
				.put()
				.uri("/curso")
				.bodyValue(cursoJPA)
				.retrieve()
				.toBodilessEntity()
				.block();
		
		HttpStatus statusCode = responseEntity.getStatusCode();
		
		System.out.println(statusCode + ". Curso Atualizado!");
	}
}
