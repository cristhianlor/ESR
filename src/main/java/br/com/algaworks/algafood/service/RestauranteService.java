package br.com.algaworks.algafood.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.model.Restaurante;
import br.com.algaworks.algafood.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private RestauranteRepository restauranteRepository;

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe código de restaurante com o código %d";

	@Autowired
	public RestauranteService(RestauranteRepository restauranteRepository) {
		this.restauranteRepository = restauranteRepository;
	}

	public Restaurante salvar(Restaurante restaurante) {

		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarOuFalhar(Integer restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
	}

	public List<Restaurante> listarTodos() {
		return restauranteRepository.findAll();
	}
	
	public List<Restaurante> consultarPorNome(String nome, Integer cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, 
			BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	public Restaurante atualizar(Integer restauranteId) {

		return restauranteRepository.findById(restauranteId).orElseThrow();
	}

	public void deletar(Integer restauranteId) {

		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId));
		}

	}

}
