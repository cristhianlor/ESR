package br.com.algaworks.algafood.service;

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

	// private CozinhaRepository cozinhaRepository;

	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe código de cozinha com o código %d";

	@Autowired
	public RestauranteService(RestauranteRepository restauranteRepository) {
		this.restauranteRepository = restauranteRepository;
	}

	public Restaurante salvar(Restaurante restaurante) {

		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarOuFalhar(Integer restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, restauranteId)));
	}

	public List<Restaurante> listarTodos() {
		return restauranteRepository.findAll();
	}

	public void deletar(Integer restauranteId) {

		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, restauranteId));
		} 

	}

}
