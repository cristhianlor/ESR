package br.com.algaworks.algafood.contoller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
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
import br.com.algaworks.algafood.model.Cozinha;
import br.com.algaworks.algafood.service.CozinhaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Cozinhas")
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody @Valid Cozinha input) {

		Cozinha cozinha = cozinhaService.salvar(input);

		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Integer cozinhaId, @RequestBody Cozinha input) {

		try {
			Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);


			if(cozinhaAtual != null) {
				BeanUtils.copyProperties(input, cozinhaAtual, "cozinhaId");

				cozinhaService.salvar(cozinhaAtual);

				return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@GetMapping
	public List<Cozinha> listar() {
		log.info("Listando cozinhas....");
		return cozinhaService.listar();
	}

	@GetMapping("/por-nome")
	public List<Cozinha> consultarCozinhaPorNome(String nome){
		log.info("Consultando cozinhas por nome....");
		return cozinhaService.consultarCozinhaPorNome(nome);
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Integer cozinhaId) {
		log.info("Iniciando busca de cozinhas por id " + cozinhaId);
		return cozinhaService.buscarOuFalhar(cozinhaId);
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> excluir(@PathVariable Integer cozinhaId) {

		cozinhaService.deletar(cozinhaId);

		return ResponseEntity.noContent().build();

	}

}
