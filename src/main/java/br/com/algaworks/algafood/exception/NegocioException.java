package br.com.algaworks.algafood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NegocioException extends ResponseStatusException {

	/**
	 * Comments
	 */
	private static final long serialVersionUID = 1L;

	public NegocioException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}

	public NegocioException(String mensagem) {
		this(HttpStatus.BAD_REQUEST, mensagem);

	}

}
