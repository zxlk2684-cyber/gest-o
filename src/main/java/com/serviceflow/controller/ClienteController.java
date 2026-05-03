package com.serviceflow.controller;

import com.serviceflow.dto.ClienteDTO;
import com.serviceflow.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "API para gestão de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Criar novo cliente")
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO dto) {
        ClienteDTO clienteCriado = clienteService.criarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter cliente por ID")
    public ResponseEntity<ClienteDTO> obterClientePorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obterClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        List<ClienteDTO> clientes = clienteService.obterTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nome")
    @Operation(summary = "Buscar clientes por nome")
    public ResponseEntity<List<ClienteDTO>> buscarPorNome(@RequestParam String nome) {
        List<ClienteDTO> clientes = clienteService.buscarClientesPorNome(nome);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/buscar/cidade")
    @Operation(summary = "Buscar clientes por cidade")
    public ResponseEntity<List<ClienteDTO>> buscarPorCidade(@RequestParam String cidade) {
        List<ClienteDTO> clientes = clienteService.buscarClientesPorCidade(cidade);
        return ResponseEntity.ok(clientes);
    }

}
