package br.com.algaworks.algafood.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeRequest {

    private String nome;
    private EstadoResponse estado;
}
