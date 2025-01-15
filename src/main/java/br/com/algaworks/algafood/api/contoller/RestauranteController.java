package br.com.algaworks.algafood.api.contoller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import br.com.algaworks.algafood.domain.dto.RestauranteRequestDTO;
import br.com.algaworks.algafood.domain.dto.RestauranteResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.exception.NegocioException;
import br.com.algaworks.algafood.domain.model.Restaurante;
import br.com.algaworks.algafood.domain.service.RestauranteService;

@Api(tags = "Restaurantes")
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService) {

		this.restauranteService = restauranteService;
	}

	@ApiOperation("Cadastra um restaurante")
	@PostMapping
	public ResponseEntity<RestauranteResponseDTO> salvar(@RequestBody @Valid RestauranteRequestDTO input) {

		try {
			//RestauranteRequestDTO request = restauranteService.salvar(input);

			return null;//ResponseEntity.status(HttpStatus.CREATED).body(request);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());

		}

	}

	@ApiOperation("Atualiza um restaurante por ID")
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Integer restauranteId,
								 @RequestBody Restaurante input) {
		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

			BeanUtils.copyProperties(input, restauranteAtual,
					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

			return restauranteService.salvar(input);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@ApiOperation("Busca um restaurante por ID")
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@ApiParam(value = "ID de um restaurante", example = "1")
							  @PathVariable Integer restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId);
	}

	@ApiOperation("Busca um restaurante por taxa frete")
	@GetMapping("/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial,
			BigDecimal taxaFinal){
		return restauranteService.restaurantesPorTaxaFrete(taxaInicial, taxaFinal);
	}

	@ApiOperation("Busca um restaurante por nome")
	@GetMapping("/consultar-por-nome")
	public List<Restaurante> consultarPorNome(String nome, Integer cozinhaId){
		return restauranteService.consultarPorNome(nome, cozinhaId);
	}

	@ApiOperation("Busca um restaurante por nome e taxa frete")
	@GetMapping("/por-nome-e-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal){
		return restauranteService.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@ApiOperation("Lista todos os restaurantes")
	@GetMapping
	public List<Restaurante> listarTodos() {

		return restauranteService.listarTodos();
	}

	@ApiOperation("Exclui um restaurante por ID")
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> deletar(@PathVariable Integer restauranteId) {

		restauranteService.deletar(restauranteId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
