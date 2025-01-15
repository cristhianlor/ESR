package br.com.algaworks.algafood.domain.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteRequestDTO {

    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaIdRequestDTO cozinha;

}
