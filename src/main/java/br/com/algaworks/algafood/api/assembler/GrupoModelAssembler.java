package br.com.algaworks.algafood.api.assembler;

import br.com.algaworks.algafood.api.model.GrupoResponse;
import br.com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelAssembler {

    private final ModelMapper modelMapper;

    // Injeção de dependência do ModelMapper via construtor
    public GrupoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GrupoResponse toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoResponse.class);
    }

    public List<GrupoResponse> toCollectionModel(List<Grupo> grupos) {
        return grupos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
