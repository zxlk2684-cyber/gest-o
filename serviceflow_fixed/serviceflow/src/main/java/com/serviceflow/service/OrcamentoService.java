package com.serviceflow.service;

import com.serviceflow.dto.OrcamentoDTO;
import com.serviceflow.entity.Orcamento;
import com.serviceflow.entity.Projeto;
import com.serviceflow.repository.OrcamentoRepository;
import com.serviceflow.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final ProjetoRepository projetoRepository;

    public OrcamentoDTO criarOrcamento(OrcamentoDTO dto) {
        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + dto.getProjetoId()));

        Orcamento orcamento = Orcamento.builder()
                .projeto(projeto)
                .numeroOrcamento(dto.getNumeroOrcamento())
                .valorMaoObra(dto.getValorMaoObra())
                .valorMateriais(dto.getValorMateriais())
                .valorAdicional(dto.getValorAdicional())
                .percentualDesconto(dto.getPercentualDesconto())
                .status(dto.getStatus())
                .dataValidade(dto.getDataValidade())
                .observacoes(dto.getObservacoes())
                .build();

        Orcamento orcamentoSalvo = orcamentoRepository.save(orcamento);
        return converterParaDTO(orcamentoSalvo);
    }

    public OrcamentoDTO obterOrcamentoPorId(Long id) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com ID: " + id));
        return converterParaDTO(orcamento);
    }

    public List<OrcamentoDTO> obterTodosOrcamentos() {
        return orcamentoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<OrcamentoDTO> obterOrcamentosPorProjeto(Long projetoId) {
        return orcamentoRepository.findByProjetoId(projetoId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<OrcamentoDTO> obterOrcamentosPorStatus(Orcamento.StatusOrcamento status) {
        return orcamentoRepository.findByStatus(status)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public OrcamentoDTO atualizarOrcamento(Long id, OrcamentoDTO dto) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com ID: " + id));

        orcamento.setNumeroOrcamento(dto.getNumeroOrcamento());
        orcamento.setValorMaoObra(dto.getValorMaoObra());
        orcamento.setValorMateriais(dto.getValorMateriais());
        orcamento.setValorAdicional(dto.getValorAdicional());
        orcamento.setPercentualDesconto(dto.getPercentualDesconto());
        orcamento.setStatus(dto.getStatus());
        orcamento.setDataValidade(dto.getDataValidade());
        orcamento.setDataAprovacao(dto.getDataAprovacao());
        orcamento.setObservacoes(dto.getObservacoes());

        Orcamento orcamentoAtualizado = orcamentoRepository.save(orcamento);
        return converterParaDTO(orcamentoAtualizado);
    }

    public void deletarOrcamento(Long id) {
        if (!orcamentoRepository.existsById(id)) {
            throw new RuntimeException("Orçamento não encontrado com ID: " + id);
        }
        orcamentoRepository.deleteById(id);
    }

    public OrcamentoDTO aprovarOrcamento(Long id) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com ID: " + id));

        orcamento.setStatus(Orcamento.StatusOrcamento.APROVADO);
        orcamento.setDataAprovacao(java.time.LocalDate.now());

        Orcamento orcamentoAtualizado = orcamentoRepository.save(orcamento);
        return converterParaDTO(orcamentoAtualizado);
    }

    private OrcamentoDTO converterParaDTO(Orcamento orcamento) {
        return OrcamentoDTO.builder()
                .id(orcamento.getId())
                .projetoId(orcamento.getProjeto().getId())
                .numeroOrcamento(orcamento.getNumeroOrcamento())
                .valorMaoObra(orcamento.getValorMaoObra())
                .valorMateriais(orcamento.getValorMateriais())
                .valorAdicional(orcamento.getValorAdicional())
                .valorTotal(orcamento.getValorTotal())
                .percentualDesconto(orcamento.getPercentualDesconto())
                .valorDesconto(orcamento.getValorDesconto())
                .status(orcamento.getStatus())
                .dataEmissao(orcamento.getDataEmissao())
                .dataValidade(orcamento.getDataValidade())
                .dataAprovacao(orcamento.getDataAprovacao())
                .observacoes(orcamento.getObservacoes())
                .build();
    }

}
