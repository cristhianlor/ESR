package br.com.algaworks.algafood.api.contoller;

import java.util.List;

import javax.validation.Valid;

import br.com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import br.com.algaworks.algafood.api.disassembler.CozinhaInputDisassembler;
import br.com.algaworks.algafood.api.model.CozinhaRequest;
import br.com.algaworks.algafood.api.model.CozinhaResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
import br.com.algaworks.algafood.domain.model.Cozinha;
import br.com.algaworks.algafood.domain.service.CozinhaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Cozinhas")
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService cozinhaService;
    private final CozinhaModelAssembler cozinhaModelAssembler;
    private final CozinhaInputDisassembler cozinhaInputDisassembler;

    public CozinhaController(CozinhaService cozinhaService,
                             CozinhaModelAssembler cozinhaModelAssembler,
                             CozinhaInputDisassembler cozinhaInputDisassembler) {
        this.cozinhaService = cozinhaService;
        this.cozinhaModelAssembler = cozinhaModelAssembler;
        this.cozinhaInputDisassembler = cozinhaInputDisassembler;
    }

    @ApiOperation("Cadastra uma cozinha")
    @PostMapping
    public ResponseEntity<CozinhaResponse> salvar(@RequestBody @Valid CozinhaRequest cozinhaRequest) {

        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaRequest);

        Cozinha cozinhaSalva = cozinhaService.salvar(cozinha);

        CozinhaResponse cozinhaResponse = cozinhaModelAssembler.toModel(cozinhaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaResponse);
    }

    @ApiOperation("Lista todas as cozinhas")
    @GetMapping
    public List<CozinhaResponse> listar() {
        log.info("Iniciando a lista de cozinhas....");
        return cozinhaModelAssembler.toCollectionModel(cozinhaService.listar());
    }

    @ApiOperation("Consulta cozinhas por nome")
    @GetMapping("/por-nome")
    public List<CozinhaResponse> consultarCozinhaPorNome(String nome) {
        log.info("Iniciando consulta de cozinhas por nome....");

        return cozinhaModelAssembler.toCollectionModel(cozinhaService.consultarCozinhaPorNome(nome));
    }

    @ApiOperation("Busca uma cozinha por ID")
    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Integer cozinhaId) {
        log.info("Iniciando busca de cozinhas por id " + cozinhaId);

        return cozinhaService.buscarOuFalhar(cozinhaId);
    }

    @ApiOperation("Atualiza uma cozinha por ID")
    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Integer cozinhaId,
                                             @RequestBody Cozinha input) {
        log.info("Iniciando atualização de cozinha com id " + cozinhaId);

        try {
            Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);

            if (cozinhaAtual != null) {
                BeanUtils.copyProperties(input, cozinhaAtual, "cozinhaId");

                cozinhaService.salvar(cozinhaAtual);

                return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @ApiOperation("Exclui uma cozinha por ID")
    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> excluir(@PathVariable Integer cozinhaId) {

        cozinhaService.deletar(cozinhaId);

        return ResponseEntity.noContent().build();

    }

}
