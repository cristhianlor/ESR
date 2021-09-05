package br.com.algaworks.algafood.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.model.Cozinha;
import br.com.algaworks.algafood.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinha")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@PostMapping
	public ResponseEntity<Cozinha> salvar (@RequestBody Cozinha cozinha) {
		
		Cozinha cz = cozinhaRepository.save(cozinha);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cz);
	}
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
}
