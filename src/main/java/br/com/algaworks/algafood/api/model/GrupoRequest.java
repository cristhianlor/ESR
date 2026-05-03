package br.com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoRequest {

    @NotNull(message = "Nome é obrigatório e não pode ser nulo")
    @ApiModelProperty(example = "Administrador", required = true)
    private String nome;
}
