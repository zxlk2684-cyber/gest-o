package com.serviceflow.service;

import com.serviceflow.dto.ProjetoDTO;
import com.serviceflow.entity.Cliente;
import com.serviceflow.entity.Projeto;
import com.serviceflow.repository.ClienteRepository;
import com.serviceflow.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ClienteRepository clienteRepository;

    public ProjetoDTO criarProjeto(ProjetoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getClienteId()));

        Projeto projeto = Projeto.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .status(dto.getStatus())
                .cliente(cliente)
                .dataInicio(dto.getDataInicio())
                .dataPrevistaConc(dto.getDataPrevistaConc())
                .valorTotal(dto.getValorTotal())
                .notas(dto.getNotas())
                .build();

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return converterParaDTO(projetoSalvo);
    }

    public ProjetoDTO obterProjetoPorId(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));
        return converterParaDTO(projeto);
    }

    public List<ProjetoDTO> obterTodosProjetos() {
        return projetoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProjetoDTO> obterProjetosPorCliente(Long clienteId) {
        return projetoRepository.findByClienteId(clienteId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProjetoDTO> obterProjetosPorStatus(Projeto.StatusProjeto status) {
        return projetoRepository.findByStatus(status)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProjetoDTO atualizarProjeto(Long id, ProjetoDTO dto) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));

        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setStatus(dto.getStatus());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataPrevistaConc(dto.getDataPrevistaConc());
        projeto.setDataConclusao(dto.getDataConclusao());
        projeto.setValorTotal(dto.getValorTotal());
        projeto.setValorPago(dto.getValorPago());
        projeto.setPercentualProgresso(dto.getPercentualProgresso());
        projeto.setNotas(dto.getNotas());

        Projeto projetoAtualizado = projetoRepository.save(projeto);
        return converterParaDTO(projetoAtualizado);
    }

    public void deletarProjeto(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado com ID: " + id);
        }
        projetoRepository.deleteById(id);
    }

    private ProjetoDTO converterParaDTO(Projeto projeto) {
        return ProjetoDTO.builder()
                .id(projeto.getId())
                .titulo(projeto.getTitulo())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .clienteId(projeto.getCliente().getId())
                .dataInicio(projeto.getDataInicio())
                .dataPrevistaConc(projeto.getDataPrevistaConc())
                .dataConclusao(projeto.getDataConclusao())
                .valorTotal(projeto.getValorTotal())
                .valorPago(projeto.getValorPago())
                .percentualProgresso(projeto.getPercentualProgresso())
                .notas(projeto.getNotas())
                .build();
    }

}
