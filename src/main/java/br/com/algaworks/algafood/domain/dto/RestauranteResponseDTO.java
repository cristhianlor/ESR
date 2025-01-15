package br.com.algaworks.algafood.domain.dto;

import br.com.algaworks.algafood.domain.model.Cozinha;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteResponseDTO {

    private Integer id;
    private String nome;
    private String taxaFrete;
    private Cozinha cozinha;
}
