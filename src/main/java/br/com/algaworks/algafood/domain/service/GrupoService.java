package br.com.algaworks.algafood.domain.service;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Grupo;
import br.com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GrupoService {

    public static final String GRUPO_NAO_ENCONTRADO = "Grupo não encontrado com o id %d";

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {

        this.grupoRepository = grupoRepository;
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {

        return grupoRepository.save(grupo);
    }

    public List<Grupo> listarTodos() {

        return grupoRepository.findAll();
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(GRUPO_NAO_ENCONTRADO, grupoId)));
    }

    @Transactional
    public Grupo atualizar(Long grupoId, Grupo grupo) {

        Grupo grupoAtual = buscarOuFalhar(grupoId);

        BeanUtils.copyProperties(grupo, grupoAtual, "id");

        return grupoRepository.save(grupoAtual);
    }

    @Transactional
    public void excluir(@PathVariable Long grupoId) {

        try {

            grupoRepository.deleteById(grupoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(GRUPO_NAO_ENCONTRADO, grupoId));
        }

    }

}

