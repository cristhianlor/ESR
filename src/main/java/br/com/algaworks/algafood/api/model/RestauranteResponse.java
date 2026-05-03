package br.com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteResponse {

    private Integer id;
    private String nome;
    private String taxaFrete;
    private CozinhaRequest cozinha;
}
