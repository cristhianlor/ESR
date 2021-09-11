package br.com.algaworks.algafood.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.model.Cozinha;
import br.com.algaworks.algafood.repository.CozinhaRepository;
import br.com.algaworks.algafood.service.CozinhaService;

@RestController
@RequestMapping("/cozinha")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {

		// Cozinha cz = cozinhaRepository.save(cozinha);

		Cozinha cz = cozinhaService.salvar(cozinha);

		return ResponseEntity.status(HttpStatus.CREATED).body(cz);
	}

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> excluir(@PathVariable Integer id) {

		try {

			Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

			if (cozinha.isPresent()) {

				cozinhaRepository.deleteById(id);

				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
