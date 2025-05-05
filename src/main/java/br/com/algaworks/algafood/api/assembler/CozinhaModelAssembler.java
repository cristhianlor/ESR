package br.com.algaworks.algafood.api.assembler;

import br.com.algaworks.algafood.api.model.CozinhaResponse;
import br.com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler {

    private final ModelMapper modelMapper;

    public CozinhaModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CozinhaResponse toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaResponse.class);
    }

    public List<CozinhaResponse> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
