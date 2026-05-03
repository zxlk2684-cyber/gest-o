package com.serviceflow.controller;

import com.serviceflow.dto.OrcamentoDTO;
import com.serviceflow.entity.Orcamento;
import com.serviceflow.service.OrcamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
@RequiredArgsConstructor
@Tag(name = "Orçamentos", description = "API para gestão de orçamentos")
public class OrcamentoController {

    private final OrcamentoService orcamentoService;

    @PostMapping
    @Operation(summary = "Criar novo orçamento")
    public ResponseEntity<OrcamentoDTO> criarOrcamento(@RequestBody OrcamentoDTO dto) {
        OrcamentoDTO orcamentoCriado = orcamentoService.criarOrcamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orcamentoCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter orçamento por ID")
    public ResponseEntity<OrcamentoDTO> obterOrcamentoPorId(@PathVariable Long id) {
        OrcamentoDTO orcamento = orcamentoService.obterOrcamentoPorId(id);
        return ResponseEntity.ok(orcamento);
    }

    @GetMapping
    @Operation(summary = "Listar todos os orçamentos")
    public ResponseEntity<List<OrcamentoDTO>> obterTodosOrcamentos() {
        List<OrcamentoDTO> orcamentos = orcamentoService.obterTodosOrcamentos();
        return ResponseEntity.ok(orcamentos);
    }

    @GetMapping("/projeto/{projetoId}")
    @Operation(summary = "Listar orçamentos de um projeto")
    public ResponseEntity<List<OrcamentoDTO>> obterOrcamentosPorProjeto(@PathVariable Long projetoId) {
        List<OrcamentoDTO> orcamentos = orcamentoService.obterOrcamentosPorProjeto(projetoId);
        return ResponseEntity.ok(orcamentos);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Listar orçamentos por status")
    public ResponseEntity<List<OrcamentoDTO>> obterOrcamentosPorStatus(@PathVariable Orcamento.StatusOrcamento status) {
        List<OrcamentoDTO> orcamentos = orcamentoService.obterOrcamentosPorStatus(status);
        return ResponseEntity.ok(orcamentos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar orçamento")
    public ResponseEntity<OrcamentoDTO> atualizarOrcamento(@PathVariable Long id, @RequestBody OrcamentoDTO dto) {
        OrcamentoDTO orcamentoAtualizado = orcamentoService.atualizarOrcamento(id, dto);
        return ResponseEntity.ok(orcamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar orçamento")
    public ResponseEntity<Void> deletarOrcamento(@PathVariable Long id) {
        orcamentoService.deletarOrcamento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/aprovar")
    @Operation(summary = "Aprovar orçamento")
    public ResponseEntity<OrcamentoDTO> aprovarOrcamento(@PathVariable Long id) {
        OrcamentoDTO orcamentoAprovado = orcamentoService.aprovarOrcamento(id);
        return ResponseEntity.ok(orcamentoAprovado);
    }

}
