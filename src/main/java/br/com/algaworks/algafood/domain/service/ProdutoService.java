package br.com.algaworks.algafood.domain.service;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Produto;
import br.com.algaworks.algafood.domain.model.Restaurante;
import br.com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Não existe produto com o código %d para o restaurante de código %d";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Transactional
    public Produto salvar(Integer restauranteId, Produto produto) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        
        produto.setRestaurante(restaurante);
        
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Integer restauranteId, Integer produtoId, Produto produto) {
        Produto produtoAtual = buscarOuFalhar(restauranteId, produtoId);
        
        produtoAtual.setNome(produto.getNome());
        produtoAtual.setDescricao(produto.getDescricao());
        produtoAtual.setPreco(produto.getPreco());
        produtoAtual.setAtivo(produto.isAtivo());
        
        return produtoRepository.save(produtoAtual);
    }

    public Produto buscarOuFalhar(Integer restauranteId, Integer produtoId) {
        return produtoRepository.findByIdAndRestauranteId(produtoId, restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId, restauranteId)));
    }

    public List<Produto> listarPorRestaurante(Integer restauranteId) {
        // Valida se o restaurante existe
        restauranteService.buscarOuFalhar(restauranteId);
        
        return produtoRepository.findByRestauranteId(restauranteId);
    }

    @Transactional
    public void deletar(Integer restauranteId, Integer produtoId) {
        Produto produto = buscarOuFalhar(restauranteId, produtoId);
        
        try {
            produtoRepository.deleteById(produto.getId());
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId, restauranteId));
        }
    }

    @Transactional
    public void ativar(Integer restauranteId, Integer produtoId) {
        Produto produto = buscarOuFalhar(restauranteId, produtoId);
        produto.setAtivo(true);
        produtoRepository.save(produto);
    }

    @Transactional
    public void desativar(Integer restauranteId, Integer produtoId) {
        Produto produto = buscarOuFalhar(restauranteId, produtoId);
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

}
