package br.com.algaworks.algafood.api.assembler;

import br.com.algaworks.algafood.api.model.ProdutoResponse;
import br.com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoResponse toModel(Produto produto) {
        ProdutoResponse produtoResponse = modelMapper.map(produto, ProdutoResponse.class);
        
        if (produto.getRestaurante() != null) {
            produtoResponse.setRestauranteId(produto.getRestaurante().getId());
        }
        
        return produtoResponse;
    }

    public List<ProdutoResponse> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toModel(produto))
                .collect(Collectors.toList());
    }
}
