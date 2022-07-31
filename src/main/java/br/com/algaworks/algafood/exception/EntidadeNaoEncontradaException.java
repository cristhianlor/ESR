package br.com.algaworks.algafood.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

	/**
	 * Comments
	 */
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
