package br.com.algaworks.algafood.api.disassembler;

import br.com.algaworks.algafood.api.model.EstadoRequest;
import br.com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {

    private final ModelMapper modelMapper;

    public EstadoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Estado toDomainObject(EstadoRequest estadoRequest) {
        return modelMapper.map(estadoRequest, Estado.class);
    }

}


