package com.serviceflow.dto;

import com.serviceflow.entity.Projeto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Projeto.StatusProjeto status;
    private Long clienteId;
    private LocalDate dataInicio;
    private LocalDate dataPrevistaConc;
    private LocalDate dataConclusao;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;
    private Integer percentualProgresso;
    private String notas;

}
