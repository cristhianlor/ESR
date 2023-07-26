package br.com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.algaworks.algafood.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		var jpql = new StringBuilder();

		var parametros = new HashMap<String, Object>();

		jpql.append("from Restaurante where 0 = 0 ");

		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");

		}

		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}

		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}

		TypedQuery<Restaurante> query = em.createQuery(jpql.toString(), Restaurante.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();

	}
}
