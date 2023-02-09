package br.com.algaworks.algafood.contoller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
import br.com.algaworks.algafood.exception.NegocioException;
import br.com.algaworks.algafood.model.Restaurante;
import br.com.algaworks.algafood.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService) {

		this.restauranteService = restauranteService;
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Integer restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId);
	}
	
	@GetMapping("/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, 
			BigDecimal taxaFinal){
		return restauranteService.restaurantesPorTaxaFrete(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/consultar-por-nome")
	public List<Restaurante> consultarPorNome(String nome, Integer cozinhaId){
		return restauranteService.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/por-nome-e-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(String nome, BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal){
		return restauranteService.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@PostMapping
	public ResponseEntity<Restaurante> salvar(@RequestBody @Valid Restaurante input) {

		try {
			Restaurante restaurante = restauranteService.salvar(input);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());

		}

	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Integer restauranteId, @RequestBody Restaurante input) {

		Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

		try {

			BeanUtils.copyProperties(input, restauranteAtual, "restauranteId");

			return restauranteService.salvar(restauranteAtual);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@GetMapping
	public List<Restaurante> listarTodos() {
		return restauranteService.listarTodos();
	}

	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> deletar(@PathVariable Integer restauranteId) {

//		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe código de cozinha com o código %d", restauranteId));

		restauranteService.deletar(restauranteId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
