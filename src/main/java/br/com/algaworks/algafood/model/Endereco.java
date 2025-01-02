package br.com.algaworks.algafood.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

	@Column(name = "end_cep", length = 8)
	private String cep;
	
	@Column(name = "end_logr", length = 50)
	private String logradouro;
	
	@Column(name = "end_num", length = 5)
	private String numero;
	
	@Column(name = "end_comp", length = 10)
	private String complemento;
	
	@Column(name = "end_bairro", length = 50)
	private String bairro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id", foreignKey = @ForeignKey(name = "endereco_cidade_id"))
	private Cidade cidade;

}
