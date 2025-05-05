package br.com.algaworks.algafood.api.disassembler;

import br.com.algaworks.algafood.api.model.RestauranteRequest;
import br.com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteRequest restauranteRequest) {

        return modelMapper.map(restauranteRequest, Restaurante.class);
    }

}
