package br.com.algaworks.algafood.core.config.modelmapper;

import br.com.algaworks.algafood.domain.dto.RestauranteRequestDTO;
import br.com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
