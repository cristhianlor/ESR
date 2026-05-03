package br.com.algaworks.algafood.api.contoller;

import br.com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import br.com.algaworks.algafood.api.disassembler.RestauranteInputDisassembler;
import br.com.algaworks.algafood.api.model.RestauranteResponse;
import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.exception.NegocioException;
import br.com.algaworks.algafood.domain.model.Restaurante;
import br.com.algaworks.algafood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Api(tags = "Restaurantes")
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;

	@ApiOperation("Cadastra um restaurante")
	@PostMapping
	public ResponseEntity<Restaurante> salvar(@RequestBody @Valid Restaurante input) {

		try {
			Restaurante request = restauranteService.salvar(input);

			return ResponseEntity.status(HttpStatus.CREATED).body(request);

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
	public List<RestauranteResponse> listarTodos() {

		return restauranteModelAssembler.toCollectionModel(restauranteService.listarTodos());
	}

	@ApiOperation("Exclui um restaurante por ID")
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> deletar(@PathVariable Integer restauranteId) {

		restauranteService.deletar(restauranteId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
