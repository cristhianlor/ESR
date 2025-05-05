package br.com.algaworks.algafood.api.disassembler;

import br.com.algaworks.algafood.api.model.CidadeRequest;
import br.com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;

public class CidadeInputDisassembler {

    private final ModelMapper modelMapper;

    public CidadeInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cidade toDomainObject(CidadeRequest cidadeRequest) {
        return modelMapper.map(cidadeRequest, Cidade.class);
    }

}
