package br.com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoResponse {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Administrador")
    private String nome;

}
