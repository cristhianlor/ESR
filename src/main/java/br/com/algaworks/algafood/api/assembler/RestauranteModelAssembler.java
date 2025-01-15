package br.com.algaworks.algafood.api.assembler;

import br.com.algaworks.algafood.domain.dto.RestauranteRequestDTO;
import br.com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;

public class RestauranteModelAssembler {

    private ModelMapper modelMapper;

    public RestauranteRequestDTO toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteRequestDTO.class);
    }

}
