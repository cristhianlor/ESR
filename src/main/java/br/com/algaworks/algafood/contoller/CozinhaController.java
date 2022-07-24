package br.com.algaworks.algafood.contoller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.exception.NegocioException;
import br.com.algaworks.algafood.exceptionhandler.Problema;
import br.com.algaworks.algafood.model.Cozinha;
import br.com.algaworks.algafood.service.CozinhaService;

@RestController
@RequestMapping("/cozinha")
public class CozinhaController {

//	@Autowired
//	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody @Valid Cozinha cozinha) {

		Cozinha cz = cozinhaService.salvar(cozinha);

		return ResponseEntity.status(HttpStatus.CREATED).body(cz);
	}

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaService.listar();
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Integer cozinhaId) {
		return cozinhaService.buscarOuFalhar(cozinhaId);
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> excluir(@PathVariable Integer cozinhaId) {

		cozinhaService.deletar(cozinhaId);

		return ResponseEntity.noContent().build();

		/*
		 * try {
		 * 
		 * Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		 * 
		 * if (cozinha.isPresent()) {
		 * 
		 * cozinhaRepository.deleteById(id);
		 * 
		 * return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); }
		 * 
		 * return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 * 
		 * } catch (EntidadeEmUsoException e) { return
		 * ResponseEntity.status(HttpStatus.CONFLICT).build(); }
		 */

	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException e){
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(
			NegocioException e){
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}
}
