package br.com.algaworks.algafood.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Restaurante;
import br.com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe restaurante com o código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public Restaurante atualizar(Integer restauranteId) {

		return restauranteRepository.findById(restauranteId).orElseThrow();
	}

	public Restaurante buscarOuFalhar(Integer restauranteId) {

		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
	}

	public List<Restaurante> listarTodos() {

		return restauranteRepository.findAll();
	}

	public List<Restaurante> consultarPorNome(String nome, Integer cozinhaId) {

		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}

	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial,
			BigDecimal taxaFinal) {

		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal){

		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@Transactional
	public void deletar(Integer restauranteId) {

		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId));
		}

	}

}
