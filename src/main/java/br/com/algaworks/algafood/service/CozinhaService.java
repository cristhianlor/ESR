package br.com.algaworks.algafood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.algaworks.algafood.model.Cozinha;
import br.com.algaworks.algafood.repository.CozinhaRepository;

@Component
public class CozinhaService {
	
	@Autowired
	public CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar (Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
}
