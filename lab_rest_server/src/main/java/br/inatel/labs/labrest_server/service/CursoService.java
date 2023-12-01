package br.inatel.labs.labrest_server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.inatel.labs.labrest_server.model.Curso;

@Service
public class CursoService
{
	private List<Curso> listaDeCursos = new ArrayList<>();
	
	@PostConstruct
	private void setup()
	{
		Curso c1 = new Curso(1, "Curso Java JPAt", 38);
		Curso c2 = new Curso(2, "Java Collections", 125);
		Curso c3 = new Curso(3, "Kubernets & Docker", 235);
		Curso c4 = new Curso(4, "React Native para aplicações móveis", 128);
		Curso c5 = new Curso(5, "Microsserviços", 15);
		
		listaDeCursos.add(c1);
		listaDeCursos.add(c2);
		listaDeCursos.add(c3);
		listaDeCursos.add(c4);
		listaDeCursos.add(c5);
	}
	
	public List<Curso> pesquisarCurso()
	{
		return listaDeCursos;
	}
	
	public List<Curso> buscarCursoPeloId(Long cursoId)
	{
		List<Curso> listaCursos = new ArrayList<>();
		for (int i=0; i < listaDeCursos.size(); i++)
		{
			if (listaDeCursos.get(i).getId() == cursoId)
			{
				listaCursos.add(listaDeCursos.get(i));
			}
		}
		return listaCursos;  
	}
	
	public Curso criarCurso(Curso curso)
	{
		Long id = new Random().nextLong();
		curso.setId(id);
		listaDeCursos.add(curso);
		return curso;
	}
	
	public void atualizarCurso(Curso curso)
	{
		listaDeCursos.remove(curso);
		listaDeCursos.add(curso);
	}
	
	public boolean removerCursoPeloId(Long cursoId)
	{
		for (Curso curso : listaDeCursos)
		{
			if (curso.getId() == cursoId)
			{
				listaDeCursos.remove(curso);
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public List<Curso> pesquisarCursoPeloFragDescricao(String fragDescricao)
	{
		if (Objects.isNull(fragDescricao) || fragDescricao.isBlank())
		{
			return List.of();
		}
		List<Curso> listaDeCursosEncontrados = this.listaDeCursos.stream()
				.filter(c -> c.getDescricao().trim().toLowerCase().contains(fragDescricao.trim().toLowerCase()))
				.collect(Collectors.toList())
				;
		return listaDeCursosEncontrados;
	}
}
