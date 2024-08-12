package br.com.viviankailany.locais.controller;

import br.com.viviankailany.locais.exception.LocalNotFoundException;
import br.com.viviankailany.locais.model.Local;
import br.com.viviankailany.locais.service.LocalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas aos locais.
 *
 * Esta classe define os endpoints para criar, listar, atualizar e deletar locais.
 */
@RestController
@RequestMapping("/locais")
public class LocalController {

    private final LocalService localService;

    /**
     * Construtor para {@code LocalController}.
     *
     * @param localService o serviço de gerenciamento de locais
     */
    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    /**
     * Cria um novo local e retorna o local criado.
     *
     * @param local o local a ser criado
     * @return a resposta contendo o local criado e o status HTTP
     */
    @PostMapping
    @Operation(summary = "Criar um novo local", description = "Cria um novo local e retorna o local criado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Local criado com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Local.class),
                    examples = @ExampleObject(value = "{\"nome\":\"Praça da fonte\", \"bairro\":\"Centro\", \"cidade\":\"Pacatuba\", \"estado\":\"CE\"}")
            )),
            @ApiResponse(responseCode = "400", description = "Os atributos são obrigatórios", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(description = "Mensagem de erro para atributos obrigatórios")
            ))
    })
    public ResponseEntity<Local> criar(@Valid @RequestBody Local local) {
        if (local.getId() != null && localService.buscarPorId(local.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Local novoLocal = localService.salvar(local);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLocal);
    }

    /**
     * Lista o local pelo ID fornecido.
     *
     * @param id o ID do local a ser buscado
     * @return a resposta contendo o local encontrado e o status HTTP
     */
    @GetMapping("/{id}")
    @Operation(summary = "Lista o local por id", description = "Lista o local pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Local.class),
                    examples = @ExampleObject(value = "{\"nome\":\"Praça da fonte\", \"bairro\":\"Centro\", \"cidade\":\"Pacatuba\", \"estado\":\"CE\"}")
            )),
            @ApiResponse(responseCode = "404", description = "Local não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(description = "Mensagem de erro para local não encontrado")
            ))
    })
    public ResponseEntity<Local> listarPorId(
            @Parameter(description = "ID do local", example = "1") @PathVariable Long id) {
        return localService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos os locais com o nome descrito.
     *
     * @param nome o nome do local a ser buscado
     * @return a resposta contendo a lista de locais encontrados e o status HTTP
     */
    @GetMapping("/nome/{nome}")
    @Operation(summary = "Lista o local por nome", description = "Lista todos os locais com o nome descrito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locais encontrados", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Local.class),
                    examples = @ExampleObject(value = "[{\"nome\":\"Praça da fonte\", \"bairro\":\"Centro\", \"cidade\":\"Pacatuba\", \"estado\":\"CE\"}]")
            )),
            @ApiResponse(responseCode = "404", description = "Nenhum local encontrado com o nome fornecido", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(description = "Mensagem de erro para nenhum local encontrado")
            ))
    })
    public ResponseEntity<List<Local>> buscaPorNome(
            @Parameter(description = "Nome do local", example = "Praça da fonte") @PathVariable String nome) {
        List<Local> locais = localService.buscaPorNome(nome);
        return ResponseEntity.ok(locais);
    }

    /**
     * Lista todos os locais por ordem de criação ascendente.
     *
     * @return a resposta contendo a lista de todos os locais e o status HTTP
     */
    @GetMapping
    @Operation(summary = "Lista todos os locais por ordem da data de criação", description = "Lista todos os locais por ordem de criação ascendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os locais do banco de dados", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Local.class),
                    examples = @ExampleObject(value = "[{\"nome\":\"Praça da fonte\", \"bairro\":\"Centro\", \"cidade\":\"Pacatuba\", \"estado\":\"CE\",  \"dataCriacao\": \"2024-08-10T17:04:10.941Z\", \"dataAtualizacao\": \"2024-08-10T17:04:10.941Z\"}]")
            ))
    })
    public ResponseEntity<List<Local>> listarTodos() {
        List<Local> locais = localService.listarDataCriacao();
        return ResponseEntity.ok(locais);
    }

    /**
     * Atualiza o local com base no ID fornecido.
     *
     * @param id o ID do local a ser atualizado
     * @param local o local com as novas informações
     * @return a resposta contendo o local atualizado e o status HTTP
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza/edita os locais de acordo com o id", description = "Atualiza o local com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local atualizado com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Local.class),
                    examples = @ExampleObject(value = "{\"nome\":\"Praça da fonte pacatuba\", \"bairro\":\"Centro\", \"cidade\":\"Pacatuba\", \"estado\":\"CE\"}")
            )),
            @ApiResponse(responseCode = "404", description = "Id não encontrado para atualização", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(description = "Mensagem de erro para ID não encontrado")
            ))
    })
    public ResponseEntity<Local> atualizar(
            @Parameter(description = "ID do local a ser atualizado", example = "1") @PathVariable Long id,
            @Valid @RequestBody Local local) {
        try {
            Local atualizado = localService.atualizar(id, local);
            return ResponseEntity.ok(atualizado);
        } catch (LocalNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta o local com o ID fornecido.
     *
     * @param id o ID do local a ser deletado
     * @return a resposta com o status HTTP
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o local de acordo com o id", description = "Deleta o local com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Local deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado para exclusão")
    })
    public ResponseEntity<Void> deletarLocal(
            @Parameter(description = "ID do local a ser deletado", example = "1") @PathVariable Long id) {
        if (localService.buscarPorId(id).isPresent()) {
            localService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta todos os locais com o nome descrito.
     *
     * @param nome o nome dos locais a serem deletados
     * @return a resposta com o status HTTP
     */
    @DeleteMapping("/nome/{nome}")
    @Operation(summary = "Deleta todos os locais pelo nome", description = "Deleta todos os locais com o nome descrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todos os locais com o nome fornecido foram deletados"),
            @ApiResponse(responseCode = "404", description = "Nenhum local encontrado com o nome fornecido")
    })
    public ResponseEntity<Void> deletarPorNome(
            @Parameter(description = "Nome dos locais a serem deletados", example = "Praça da fonte") @PathVariable String nome) {
        localService.deletarPorNome(nome);
        return ResponseEntity.noContent().build();
    }
}
