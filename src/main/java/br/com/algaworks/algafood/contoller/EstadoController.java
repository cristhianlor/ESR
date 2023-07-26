package br.com.algaworks.algafood.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.model.Estado;
import br.com.algaworks.algafood.service.EstadoService;

@RestController
@RequestMapping("/estado")
public class EstadoController {

	private EstadoService estadoService;

	@Autowired
	public EstadoController(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	@GetMapping
	public List<Estado> listar() {
		return estadoService.listarTodos();
	}

	@GetMapping("{estadoId}")
	public Estado buscarPorId(@PathVariable Integer estadoId) {
		return estadoService.buscarOuFalhar(estadoId);
	}

	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado input) {

		Estado estado = estadoService.salvar(input);

		return ResponseEntity.status(HttpStatus.CREATED).body(estado);

	}

	@DeleteMapping("/{estadoId}")
	public void deletar(@PathVariable Integer estadoId) {
		estadoService.excluir(estadoId);
	}

}
