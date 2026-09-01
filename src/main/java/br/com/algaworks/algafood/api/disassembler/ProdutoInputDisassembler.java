package br.com.algaworks.algafood.api.disassembler;

import br.com.algaworks.algafood.api.model.ProdutoRequest;
import br.com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoRequest produtoRequest) {
        return modelMapper.map(produtoRequest, Produto.class);
    }

    public void copyToDomainObject(ProdutoRequest produtoRequest, Produto produto) {
        modelMapper.map(produtoRequest, produto);
    }

}
