package br.com.algaworks.algafood.api.contoller;

import java.util.List;

import br.com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.algaworks.algafood.domain.model.Cidade;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	private CidadeService cidadeService;

	@Autowired
	public CidadeController(CidadeService cidadeService) {

		this.cidadeService = cidadeService;
	}

	@PostMapping
	public ResponseEntity<Cidade> salvarCidade(@RequestBody Cidade input) {
		try {
			Cidade cidade = cidadeService.salvar(input);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(e.getMessage());
		}
	}

	@GetMapping
	public List<Cidade> listarTodasAsCidades() {

		return cidadeService.listarCidades();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Integer cidadeId) {

		return cidadeService.buscarCidade(cidadeId);
	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Integer cidadeId) {

		cidadeService.remover(cidadeId);

		return ResponseEntity.noContent().build();

	}

}
