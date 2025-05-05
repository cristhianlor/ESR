package br.com.algaworks.algafood.api.disassembler;

import br.com.algaworks.algafood.api.model.CozinhaRequest;
import br.com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler {

    private final ModelMapper modelMapper;

    public CozinhaInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cozinha toDomainObject(CozinhaRequest cozinhaRequest) {
        return modelMapper.map(cozinhaRequest, Cozinha.class);
    }
}
