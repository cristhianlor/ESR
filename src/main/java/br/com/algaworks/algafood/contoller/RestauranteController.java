package br.com.algaworks.algafood.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.model.Restaurante;
import br.com.algaworks.algafood.repository.RestauranteRepository;
import br.com.algaworks.algafood.service.RestauranteService;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

	private RestauranteRepository restauranteRepository;

	private RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService) {
		this.restauranteRepository = restauranteRepository;
		this.restauranteService = restauranteService;
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscarOuFalhar(@PathVariable Integer restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe código de cozinha com o código %d"));
	}

	@PostMapping
	public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {

		Restaurante rest = restauranteService.salvar(restaurante);

		return ResponseEntity.status(HttpStatus.CREATED).body(rest);
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable Integer restauranteId,
			@RequestBody Restaurante restaurante) {

		return null;

	}

	@GetMapping
	public List<Restaurante> listarTodos() {
		return restauranteService.listarTodos();
	}
	
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> deletar(@PathVariable Integer restauranteId){
		
//		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe código de cozinha com o código %d"));
		
		restauranteRepository.deleteById(restauranteId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
