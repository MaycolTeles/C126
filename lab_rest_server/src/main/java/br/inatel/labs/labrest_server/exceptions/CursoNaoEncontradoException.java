package br.inatel.labs.labrest_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CursoNaoEncontradoException extends ResponseStatusException
{
	private static final long serialVersionUID = -2503400944594581603L;

	public CursoNaoEncontradoException(Long cursoId)
	{
		super(HttpStatus.NOT_FOUND, String.format("Curso " + cursoId + " não encontrado."));
	}
}
