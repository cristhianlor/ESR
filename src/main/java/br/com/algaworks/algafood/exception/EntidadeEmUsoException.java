package br.com.algaworks.algafood.exception;

public class EntidadeEmUsoException extends RuntimeException {

	/**
	 * Comentários
	 */
	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException (String mensagem) {
		super(mensagem);
		}
	
}
