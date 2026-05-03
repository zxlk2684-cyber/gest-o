package com.serviceflow.service;

import com.serviceflow.dto.ClienteDTO;
import com.serviceflow.entity.Cliente;
import com.serviceflow.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteDTO criarCliente(ClienteDTO dto) {
        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .telefonSecundario(dto.getTelefonSecundario())
                .endereco(dto.getEndereco())
                .numeroEndereco(dto.getNumeroEndereco())
                .complementoEndereco(dto.getComplementoEndereco())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .observacoes(dto.getObservacoes())
                .build();

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return converterParaDTO(clienteSalvo);
    }

    public ClienteDTO obterClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return converterParaDTO(cliente);
    }

    public List<ClienteDTO> obterTodosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setTelefonSecundario(dto.getTelefonSecundario());
        cliente.setEndereco(dto.getEndereco());
        cliente.setNumeroEndereco(dto.getNumeroEndereco());
        cliente.setComplementoEndereco(dto.getComplementoEndereco());
        cliente.setCidade(dto.getCidade());
        cliente.setEstado(dto.getEstado());
        cliente.setCep(dto.getCep());
        cliente.setObservacoes(dto.getObservacoes());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return converterParaDTO(clienteAtualizado);
    }

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public List<ClienteDTO> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ClienteDTO> buscarClientesPorCidade(String cidade) {
        return clienteRepository.findByCidade(cidade)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private ClienteDTO converterParaDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .telefonSecundario(cliente.getTelefonSecundario())
                .endereco(cliente.getEndereco())
                .numeroEndereco(cliente.getNumeroEndereco())
                .complementoEndereco(cliente.getComplementoEndereco())
                .cidade(cliente.getCidade())
                .estado(cliente.getEstado())
                .cep(cliente.getCep())
                .observacoes(cliente.getObservacoes())
                .build();
    }

}
