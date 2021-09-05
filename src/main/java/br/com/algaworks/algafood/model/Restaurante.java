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
@Table(name = "tb_restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome_restaurante", nullable = false, length = 70)
	private String nome;
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete = BigDecimal.ZERO;
	@ManyToOne
	@JoinColumn(name = "cozinha_id")
	private Cozinha cozinha;

	public Restaurante(String nome, BigDecimal taxaFrete, Cozinha cozinha) {
		this.nome = nome;
		this.taxaFrete = taxaFrete;
		this.cozinha = cozinha;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public Cozinha getCozinha() {
		return cozinha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
