package br.com.algaworks.algafood.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

	@Column(name = "endereco_cep", length = 8)
	private String cep;
	
	@Column(name = "endereco_logr", length = 50)
	private String logradouro;
	
	@Column(name = "endereco_num", length = 5)
	private String numero;
	
	@Column(name = "endereco_comp", length = 10)
	private String complemento;
	
	@Column(name = "endereco_bairro", length = 50)
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;

}
