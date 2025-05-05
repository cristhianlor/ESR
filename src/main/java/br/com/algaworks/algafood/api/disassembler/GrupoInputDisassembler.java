package br.com.algaworks.algafood.api.disassembler;

import br.com.algaworks.algafood.api.model.GrupoRequest;
import br.com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {

    private final ModelMapper modelMapper;

    // Injeção de dependência do ModelMapper via construtor
    public GrupoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Grupo toDomainObject(GrupoRequest grupoRequest) {

        return modelMapper.map(grupoRequest, Grupo.class);

    }

}
