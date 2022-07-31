package br.com.algaworks.algafood.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.model.Cidade;
import br.com.algaworks.algafood.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	private CidadeRepository cidadeRepository;

	@Autowired
	public CidadeController(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}

	@PostMapping
	public ResponseEntity<Cidade> salvarCidade(@RequestBody Cidade input) {

		Cidade cidade = cidadeRepository.save(input);

		return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
	}

	@GetMapping
	public List<Cidade> listarTodasAsCidades() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Optional<Cidade> buscar(@PathVariable Integer cidadeId) {
		return cidadeRepository.findById(cidadeId);
	}

}
