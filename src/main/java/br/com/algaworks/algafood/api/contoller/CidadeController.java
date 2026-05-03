package br.com.algaworks.algafood.api.contoller;

import java.util.List;

import br.com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.algaworks.algafood.domain.model.Cidade;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {

        this.cidadeService = cidadeService;
    }

    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    public ResponseEntity<Cidade> salvarCidade(@RequestBody Cidade input) {
        try {
            Cidade cidade = cidadeService.salvar(input);

            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(e.getMessage());
        }
    }

    @ApiOperation("Lista todas as cidades")
    @GetMapping
    public List<Cidade> listarTodasAsCidades() {

        return cidadeService.listarCidades();
    }

    @ApiOperation("Busca uma cidade por ID")
    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Integer cidadeId) {

        return cidadeService.buscarCidade(cidadeId);
    }

    @ApiOperation("Exclui uma cidade")
    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Integer cidadeId) {

        cidadeService.remover(cidadeId);

        return ResponseEntity.noContent().build();

    }

}
