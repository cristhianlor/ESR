package br.com.algaworks.algafood.api.assembler;

import br.com.algaworks.algafood.api.model.CidadeResponse;
import br.com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {

    private final ModelMapper modelMapper;

    public CidadeModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CidadeResponse toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeResponse.class);
    }

    public List<CidadeResponse> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
