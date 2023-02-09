package br.com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal) {

		var jpql = "from Restaurante where nome like :nome " + "and taxaFrete between :taxaInicial and :taxaFinal";

		return em.createQuery(jpql, Restaurante.class).setParameter("nome", "%" + nome + "%")
				.setParameter("taxaInicial", taxaFreteInicial).setParameter("taxaFinal", taxaFreteFinal)
				.getResultList();

	}
}
