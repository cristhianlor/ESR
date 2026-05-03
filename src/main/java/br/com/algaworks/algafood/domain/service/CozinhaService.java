package br.com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Cozinha;
import br.com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CozinhaService {

    private static final String MSG_COZINHA_EM_USO = "A cozinha de código %d não pode ser removida, pois está em uso";

    private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe código de cozinha com o código %d";


    public final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Integer cozinhaId) {

        return cozinhaRepository.findById(cozinhaId).orElseThrow();
    }

    public List<Cozinha> consultarCozinhaPorNome(String nome) {

        return cozinhaRepository.findByNome(nome);
    }

    public List<Cozinha> listar() {

        return cozinhaRepository.findAll();
    }

    public Cozinha buscarOuFalhar(Integer cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
    }

    @Transactional
    public void deletar(Integer cozinhaId) {

        try {

            cozinhaRepository.deleteById(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
        }
    }
}
