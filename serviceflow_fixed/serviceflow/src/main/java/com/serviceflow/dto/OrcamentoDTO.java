package com.serviceflow.dto;

import com.serviceflow.entity.Orcamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrcamentoDTO {

    private Long id;
    private Long projetoId;
    private String numeroOrcamento;
    private BigDecimal valorMaoObra;
    private BigDecimal valorMateriais;
    private BigDecimal valorAdicional;
    private BigDecimal valorTotal;
    private BigDecimal percentualDesconto;
    private BigDecimal valorDesconto;
    private Orcamento.StatusOrcamento status;
    private LocalDate dataEmissao;
    private LocalDate dataValidade;
    private LocalDate dataAprovacao;
    private String observacoes;

}
