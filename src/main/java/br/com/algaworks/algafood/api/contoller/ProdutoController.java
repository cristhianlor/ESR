package br.com.algaworks.algafood.api.contoller;

import br.com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import br.com.algaworks.algafood.api.disassembler.ProdutoInputDisassembler;
import br.com.algaworks.algafood.api.model.ProdutoRequest;
import br.com.algaworks.algafood.api.model.ProdutoResponse;
import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.exception.NegocioException;
import br.com.algaworks.algafood.domain.model.Produto;
import br.com.algaworks.algafood.domain.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @ApiOperation("Cadastra um produto para um restaurante")
    @PostMapping
    public ResponseEntity<ProdutoResponse> salvar(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {

        try {
            Produto produto = produtoInputDisassembler.toDomainObject(produtoRequest);
            produto = produtoService.salvar(restauranteId, produto);

            ProdutoResponse produtoResponse = produtoModelAssembler.toModel(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @ApiOperation("Atualiza um produto de um restaurante por ID")
    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> atualizar(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId,
            @ApiParam(value = "ID de um produto", example = "1")
            @PathVariable Integer produtoId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {

        try {
            Produto produto = produtoInputDisassembler.toDomainObject(produtoRequest);
            produto = produtoService.atualizar(restauranteId, produtoId, produto);

            ProdutoResponse produtoResponse = produtoModelAssembler.toModel(produto);

            return ResponseEntity.ok(produtoResponse);

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @ApiOperation("Busca um produto de um restaurante por ID")
    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> buscar(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId,
            @ApiParam(value = "ID de um produto", example = "1")
            @PathVariable Integer produtoId) {

        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        ProdutoResponse produtoResponse = produtoModelAssembler.toModel(produto);

        return ResponseEntity.ok(produtoResponse);
    }

    @ApiOperation("Lista todos os produtos de um restaurante")
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodos(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId) {

        List<Produto> produtos = produtoService.listarPorRestaurante(restauranteId);
        List<ProdutoResponse> produtosResponse = produtoModelAssembler.toCollectionModel(produtos);

        return ResponseEntity.ok(produtosResponse);
    }

    @ApiOperation("Exclui um produto de um restaurante por ID")
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletar(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId,
            @ApiParam(value = "ID de um produto", example = "1")
            @PathVariable Integer produtoId) {

        produtoService.deletar(restauranteId, produtoId);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Ativa um produto de um restaurante por ID")
    @PutMapping("/{produtoId}/ativo")
    public ResponseEntity<Void> ativar(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId,
            @ApiParam(value = "ID de um produto", example = "1")
            @PathVariable Integer produtoId) {

        produtoService.ativar(restauranteId, produtoId);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Desativa um produto de um restaurante por ID")
    @DeleteMapping("/{produtoId}/ativo")
    public ResponseEntity<Void> desativar(
            @ApiParam(value = "ID de um restaurante", example = "1")
            @PathVariable Integer restauranteId,
            @ApiParam(value = "ID de um produto", example = "1")
            @PathVariable Integer produtoId) {

        produtoService.desativar(restauranteId, produtoId);

        return ResponseEntity.noContent().build();
    }

}
