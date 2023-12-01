package br.inatel.labs.labrest_client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest_client.model.Curso;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CursoControllerTest
{	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void cursosDevemSerListados()
	{
		webTestClient.get()
		.uri("/curso")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody()
		.returnResult();
	}

	@Test
	void dadoCursoIdValido_quandoGetCursoPeloId_entaoResponseComCursoCValido()
	{
		Long cursoValido = 1L;
		
		Curso cursoRespondido = webTestClient.get()
				.uri("/curso/" + cursoValido)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertNotNull(cursoRespondido);
		assertEquals(cursoRespondido.getId(), cursoValido);
	}
	
	@Test
	void dadoCursoIdValido_quandoGetCursoPeloId_entaoResponseComStatusCreatedCursoValido()
	{
		Curso cursoJPA = new Curso();
		cursoJPA.setDescricao("Curso Java JPA");
		cursoJPA.setCargaHoraria(85);
		
		Curso cursoResponse = webTestClient.post()
				.bodyValue(cursoJPA)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoResponse).isNotNull();
		assertThat(cursoResponse.getId()).isNotNull();
	}
	
	@Test
	void dadoCursoIdValido_quandoPutCurso_entaoResponseComStatusAccepted()
	{
		Curso curso = new Curso();
		curso.setId(1L);
		curso.setDescricao("Curso voltado para ensino de Design Patterns");
		curso.setCargaHoraria(155);
		
		webTestClient.put()
		.uri("/curso")
		.bodyValue(curso)
		.exchange()
		.expectStatus()
		.isAccepted()
		.expectBody()
		.isEmpty();
	}
	
	@Test
	void dadoCursoIdValido_quandoDeleteCursoPeloId_entaoResponseComStatusNoContent() 
	{
		Long cursoValido = 2L;
		
		webTestClient.delete()
		.uri("/curso/" + cursoValido)
		.exchange()
		.expectStatus()
		.isNoContent()
		.expectBody()
		.isEmpty();
	}
	
	@Test
	void dadoCursoIdValido_quandoDeleteCursoPeloId_entaoResponseComStatusNotFound()
	{
		Long cursoValido = 99L;
		
		webTestClient.delete()
		.uri("/curso/" + cursoValido)
		.exchange()
		.expectStatus()
		.isNotFound();
	}
}
