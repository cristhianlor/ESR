package br.com.algaworks.algafood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.model.Estado;
import br.com.algaworks.algafood.repository.EstadoRepository;

@Service
public class EstadoService {
	
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
	
	public Estado bucarPorId (Integer id) {
		return estadoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade com o id " + id + " não foi localizada/não existe"));
	}
	
	public void excluir (Integer id) {
		estadoRepository.deleteById(id);
	}
}
