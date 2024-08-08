package br.com.viviankailany.locais.controller;

import br.com.viviankailany.locais.model.Local;
import br.com.viviankailany.locais.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/locais")
public class LocalController {

    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<Local> criar(@Valid @RequestBody Local local) {
        Local novoLocal = localService.salvar(local);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLocal);
    }

    @GetMapping
    public ResponseEntity<List<Local>> listarTodos() {
        List<Local> locais = localService.listar();
        return ResponseEntity.ok(locais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> atualizar(@PathVariable Long id, @Valid @RequestBody Local local) {
        return localService.buscarPorId(id)
                .map(existingLocal -> {
                    existingLocal.setNome(local.getNome());
                    existingLocal.setDescricao(local.getDescricao());
                    existingLocal.setDataAtualizacao(LocalDateTime.now());
                    Local atualizado = localService.salvar(existingLocal);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }




}
