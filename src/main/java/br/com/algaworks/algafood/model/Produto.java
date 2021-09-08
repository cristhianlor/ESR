package br.com.algaworks.algafood.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nm_prod", nullable = false, length = 70)
	private String nome;
	@Column(name = "desc_prod", nullable = false, length = 70)
	private String descricao;
	@Column(name = "vlr_prod", nullable = false)
	private BigDecimal preco;
	@Column(name = "prod_ativo", nullable = false)
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;

	public Produto() {
	}

	public Produto(String nome, String descricao, BigDecimal preco, boolean ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public boolean isAtivo() {
		return ativo;
	}

}
