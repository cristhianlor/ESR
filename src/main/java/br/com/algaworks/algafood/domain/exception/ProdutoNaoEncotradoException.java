package br.com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncotradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncotradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncotradoException(Long produtoId, Long restauranteId) {
        this(String.format("Não existe um cadastro de produto com código %d " +
                "para o restaurante de código %d", produtoId, restauranteId));
    }
}
