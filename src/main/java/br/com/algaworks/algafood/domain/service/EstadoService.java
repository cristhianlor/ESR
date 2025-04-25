package br.com.algaworks.algafood.domain.service;

import java.util.List;

import br.com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Estado;
import br.com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";
    private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe estado com o código %d";

    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {

        this.estadoRepository = estadoRepository;
    }
    @Transactional
    public Estado salvar(Estado estado) {

        return estadoRepository.save(estado);
    }

    public List<Estado> listarTodos() {

        return estadoRepository.findAll();
    }

    public Estado buscarOuFalhar(Integer estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));
    }

    @Transactional
    public void excluir(Integer id) {
        try{
            estadoRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
        }
    }

}
