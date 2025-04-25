package br.com.algaworks.algafood.api.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteRequest {

    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaIdRequest cozinha;

}
