package br.com.algaworks.algafood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
