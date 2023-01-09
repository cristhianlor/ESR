package br.com.algaworks.algafood.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@EqualsAndHashCode.Include
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
	@JoinColumn(name = "restaurante_id", nullable = false, 
	foreignKey = @ForeignKey(name = "restaurante_id"))
	private Restaurante restaurante;

}
