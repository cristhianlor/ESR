package br.com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.model.Estado;
import br.com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe estado com o código %d";

	private EstadoRepository estadoRepository;
	
	@Autowired
	public EstadoService(EstadoRepository estadoRepository) {

		this.estadoRepository = estadoRepository;
	}
	
	public Estado salvar (Estado estado) {

		return estadoRepository.save(estado);
	}
	
	public List<Estado> listarTodos (){

		return estadoRepository.findAll();
	}
	
	public Estado buscarOuFalhar (Integer estadoId) {
		return estadoRepository.findById(estadoId)
		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));
	}
	
	public void excluir (Integer id) {

		estadoRepository.deleteById(id);
	}
	
	
}
