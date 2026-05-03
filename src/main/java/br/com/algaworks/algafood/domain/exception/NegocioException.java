package br.com.algaworks.algafood.domain.exception;

public class NegocioException extends RuntimeException {

	/**
	 * Comments
	 */
	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {

		super(mensagem);
	}

}
