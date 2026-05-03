package br.com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaRequest {

    @NotBlank
    @NotNull
    @ApiModelProperty(example = "Brasileira", required = true)
    private String nome;

}
