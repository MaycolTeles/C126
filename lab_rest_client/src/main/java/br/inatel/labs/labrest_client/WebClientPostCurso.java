package br.inatel.labs.labrest_client;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest_client.model.Curso;

public class WebClientPostCurso
{	
	public static void main(String [] args)
	{
		Curso cursoCollections = new Curso();
		cursoCollections.setDescricao("Java Collections");
		cursoCollections.setCargaHoraria(125);
		
		Curso curso = WebClient.create("http://localhost:8080")
				.post()
				.uri("/curso")
				.bodyValue(cursoCollections)
				.retrieve()
				.bodyToMono(Curso.class)
				.block();
		
		System.out.println("Curso: ");
		System.out.println(curso);
	}
}
