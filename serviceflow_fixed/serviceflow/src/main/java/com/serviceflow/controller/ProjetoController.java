package com.serviceflow.controller;

import com.serviceflow.dto.ProjetoDTO;
import com.serviceflow.entity.Projeto;
import com.serviceflow.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
@Tag(name = "Projetos", description = "API para gestão de projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping
    @Operation(summary = "Criar novo projeto")
    public ResponseEntity<ProjetoDTO> criarProjeto(@RequestBody ProjetoDTO dto) {
        ProjetoDTO projetoCriado = projetoService.criarProjeto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter projeto por ID")
    public ResponseEntity<ProjetoDTO> obterProjetoPorId(@PathVariable Long id) {
        ProjetoDTO projeto = projetoService.obterProjetoPorId(id);
        return ResponseEntity.ok(projeto);
    }

    @GetMapping
    @Operation(summary = "Listar todos os projetos")
    public ResponseEntity<List<ProjetoDTO>> obterTodosProjetos() {
        List<ProjetoDTO> projetos = projetoService.obterTodosProjetos();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar projetos de um cliente")
    public ResponseEntity<List<ProjetoDTO>> obterProjetosPorCliente(@PathVariable Long clienteId) {
        List<ProjetoDTO> projetos = projetoService.obterProjetosPorCliente(clienteId);
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Listar projetos por status")
    public ResponseEntity<List<ProjetoDTO>> obterProjetosPorStatus(@PathVariable Projeto.StatusProjeto status) {
        List<ProjetoDTO> projetos = projetoService.obterProjetosPorStatus(status);
        return ResponseEntity.ok(projetos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar projeto")
    public ResponseEntity<ProjetoDTO> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO dto) {
        ProjetoDTO projetoAtualizado = projetoService.atualizarProjeto(id, dto);
        return ResponseEntity.ok(projetoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar projeto")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

}
