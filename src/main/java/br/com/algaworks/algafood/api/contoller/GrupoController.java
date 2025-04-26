package br.com.algaworks.algafood.api.contoller;

import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.exception.NegocioException;
import br.com.algaworks.algafood.domain.model.Grupo;
import br.com.algaworks.algafood.domain.service.GrupoService;
import io.swagger.annotations.Api;
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

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping
    public ResponseEntity<Grupo> salvar(@RequestBody @Valid Grupo grupo) {

        Grupo newGrupo = grupoService.salvar(grupo);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Grupo> listar() {

        return grupoService.listarTodos();
    }

    @GetMapping("/{grupoId}")
    public Grupo buscarPorId(@PathVariable Long grupoId) {

        return grupoService.buscarOuFalhar(grupoId);
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<Grupo> atualizar(@PathVariable Long grupoId,
                                           @RequestBody Grupo grupo) {
        try {
            Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

            if (grupoAtual != null) {
                BeanUtils.copyProperties(grupo, grupoAtual, "id");

                grupoService.salvar(grupoAtual);

                return ResponseEntity.status(HttpStatus.OK).body(grupoAtual);

            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Grupo> deletar(@PathVariable Long grupoId) {

        grupoService.excluir(grupoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
