package br.com.algaworks.algafood.api.model;

import br.com.algaworks.algafood.domain.model.Cozinha;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteResponse {

    private Integer id;
    private String nome;
    private String taxaFrete;
    private CozinhaIdRequest cozinha;
}
