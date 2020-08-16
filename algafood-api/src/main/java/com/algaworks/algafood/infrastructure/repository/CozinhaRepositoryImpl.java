package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	// Método para listar as cozinhas cadastradas
	@Override
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	// Método para buscar uma cozinha através de 01 id 
	@Override
	public Cozinha buscar (Long id) {
		return manager.find(Cozinha.class, id);
	} 
	
	// Método para remover uma cozinha
	@Transactional
	@Override
	public void remover (Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}
	
	// Método para cadastrar uma cozinha
	@Transactional
	@Override
	public Cozinha salvar (Cozinha cozinha) {
		return manager.merge(cozinha);
	}

}
