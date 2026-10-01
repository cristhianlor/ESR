package br.com.algaworks.algafood.domain.repository;

import br.com.algaworks.algafood.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByRestauranteId(Integer restauranteId);

    Optional<Produto> findByIdAndRestauranteId(Integer id, Integer restauranteId);

}
