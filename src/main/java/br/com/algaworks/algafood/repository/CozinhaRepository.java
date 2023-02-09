package br.com.algaworks.algafood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Integer>{
	
	List<Cozinha> findByNome (String nome);
	
}
