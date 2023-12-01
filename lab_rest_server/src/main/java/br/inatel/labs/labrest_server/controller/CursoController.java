package br.inatel.labs.labrest_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.inatel.labs.labrest_server.exceptions.CursoNaoEncontradoException;
import br.inatel.labs.labrest_server.model.Curso;
import br.inatel.labs.labrest_server.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController
{
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public List<Curso> listarCursos()
	{
		return cursoService.pesquisarCurso();
	}
	
	@GetMapping("/{id}")
	public Curso buscar(@PathVariable("id") Long cursoId)
	{
		List<Curso> listaCurso = cursoService.buscarCursoPeloId(cursoId);
		Curso curso;
		if(listaCurso.size() > 0)
		{
			curso = listaCurso.get(0);
			return curso;
		}
		else
		{
			String message = String.format("Nenhum curso encontrado com o id [%s]", cursoId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Curso criar(@RequestBody Curso curso)
	{
		return cursoService.criarCurso(curso);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void atualizar(@RequestBody Curso curso)
	{
		cursoService.atualizarCurso(curso);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long cursoIdParaRemover)
	{
		boolean verifica = cursoService.removerCursoPeloId(cursoIdParaRemover);
		
		if(verifica == false)
		{
			throw new CursoNaoEncontradoException(cursoIdParaRemover);
		}
	}
	
	@GetMapping("/pesquisa")
	public List<Curso> listarPeloFragDescricao(@RequestParam("descricao") String fragDescricao)
	{
		return cursoService.pesquisarCursoPeloFragDescricao(fragDescricao);
	}
}
