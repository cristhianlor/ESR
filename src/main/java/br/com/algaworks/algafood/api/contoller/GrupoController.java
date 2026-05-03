package br.com.algaworks.algafood.api.contoller;

import br.com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import br.com.algaworks.algafood.api.disassembler.GrupoInputDisassembler;
import br.com.algaworks.algafood.api.model.GrupoRequest;
import br.com.algaworks.algafood.api.model.GrupoResponse;
import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.exception.NegocioException;
import br.com.algaworks.algafood.domain.model.Grupo;
import br.com.algaworks.algafood.domain.service.GrupoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    private final GrupoModelAssembler grupoModelAssembler;

    private final GrupoInputDisassembler grupoInputDisassembler;

    public GrupoController(GrupoService grupoService, GrupoModelAssembler grupoModelAssembler,
                           GrupoInputDisassembler grupoInputDisassembler) {
        this.grupoService = grupoService;
        this.grupoModelAssembler = grupoModelAssembler;
        this.grupoInputDisassembler = grupoInputDisassembler;
    }

    @ApiOperation("Cadastra um grupo")
    @PostMapping
    public ResponseEntity<GrupoResponse> salvar(@ApiParam(name = "body", value = "Representação de um novo grupo")
                                                @RequestBody @Valid GrupoRequest grupoRequest) {

        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoRequest);

        Grupo grupoSalvo = grupoService.salvar(grupo);

        GrupoResponse grupoResponse = grupoModelAssembler.toModel(grupoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoResponse);
    }

    @ApiOperation("Lista todos os grupos")
    @GetMapping
    public List<GrupoResponse> listar() {

        return grupoModelAssembler.toCollectionModel(grupoService.listarTodos());
    }

    @ApiOperation("Busca um grupo por ID")
    @GetMapping("/{grupoId}")
    public Grupo buscarPorId(@ApiParam(value = "ID de um grupo", example = "1")
                             @PathVariable Long grupoId) {

        return grupoService.buscarOuFalhar(grupoId);
    }

    @ApiOperation("Atualiza um grupo por ID")
    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoResponse> atualizar(@ApiParam(value = "ID de um grupo", example = "1")
                                                   @PathVariable Long grupoId,
                                                   @ApiParam(name = "body", value = "Representação de um grupo com os " +
                                                           "dados atualizados")
                                                   @RequestBody GrupoRequest grupoRequest) {
        try {
            Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

            grupoInputDisassembler.toDomainObject(grupoRequest);

            if (grupoAtual != null) {
                BeanUtils.copyProperties(grupoRequest, grupoAtual, "id");

                // Salva o grupo atualizado
                Grupo grupoSalvo = grupoService.salvar(grupoAtual);

                // Converte o modelo de domínio atualizado para o modelo de resposta
                GrupoResponse grupoResponse = grupoModelAssembler.toModel(grupoSalvo);

                return ResponseEntity.status(HttpStatus.OK).body(grupoResponse);

            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @ApiOperation("Exclui um grupo por ID")
    @DeleteMapping("/{grupoId}")
    public ResponseEntity<GrupoResponse> deletar(@ApiParam(value = "ID de um grupo", example = "1")
                                                 @PathVariable Long grupoId) {

        grupoService.excluir(grupoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
