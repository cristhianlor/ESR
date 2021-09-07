package br.com.algaworks.algafood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
