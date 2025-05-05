package br.com.algaworks.algafood.api.assembler;

import br.com.algaworks.algafood.api.model.EstadoResponse;
import br.com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelAssembler {

    private final ModelMapper modelMapper;

    public EstadoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EstadoResponse toModel(Estado estado) {
        return modelMapper.map(estado, EstadoResponse.class);
    }

    public List<EstadoResponse> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
