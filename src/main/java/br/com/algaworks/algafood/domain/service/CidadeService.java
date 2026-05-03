package br.com.algaworks.algafood.domain.service;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Cidade;
import br.com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    private static final String MSG_CIDADE_NAO_ENCONTRADO = "Não existe cidade com o código %d";

    private CidadeRepository cidadeRepository;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository) {

        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {

        return cidadeRepository.save(cidade);
    }

    public List<Cidade> listarCidades() {

        return cidadeRepository.findAll();
    }

    public Cidade buscarCidade(Integer id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public void remover(Integer cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADO, cidadeId));
        }
    }

    public Cidade atualizar(Cidade cidade) {

        return cidadeRepository.save(cidade);
    }
}
