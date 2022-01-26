package br.com.algaworks.algafood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends ResponseStatusException {

	/**
	 * Comentários
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}

	public EntidadeEmUsoException(String mensagem) {
		this(HttpStatus.CONFLICT, mensagem);
	}

}
