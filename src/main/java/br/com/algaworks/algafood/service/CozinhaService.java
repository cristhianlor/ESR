package br.com.algaworks.algafood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.model.Cozinha;
import br.com.algaworks.algafood.repository.CozinhaRepository;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "A cozinha de código %d não pode ser removida, pois está em uso";

	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe código de cozinha com o código %d";

	@Autowired
	public CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	public void deletar(Integer cozinhaId) {

		try {

			cozinhaRepository.deleteById(cozinhaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));
			// throw new ResponseStatusException(HttpStatus.CONFLICT,
			// String.format(MSG_COZINHA_EM_USO, cozinhaId));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		} // throw new ResponseStatusException(HttpStatus.NOT_FOUND,
			// String.format(MSG_COZINHA_EM_USO, cozinhaId));
	}

	public Cozinha buscarOuFalhar(Integer cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
	}
	
	public Cozinha atualizar (Integer cozinhaId) {
		
		
		return cozinhaRepository.findById(cozinhaId).orElseThrow();
	}

}
