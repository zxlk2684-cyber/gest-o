package com.serviceflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orcamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(name = "numero_orcamento", nullable = false, unique = true)
    private String numeroOrcamento;

    @Column(name = "valor_mao_obra", precision = 10, scale = 2)
    private BigDecimal valorMaoObra;

    @Column(name = "valor_materiais", precision = 10, scale = 2)
    private BigDecimal valorMateriais;

    @Column(name = "valor_adicional", precision = 10, scale = 2)
    private BigDecimal valorAdicional;

    @Column(name = "valor_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "percentual_desconto", precision = 5, scale = 2)
    private BigDecimal percentualDesconto;

    @Column(name = "valor_desconto", precision = 10, scale = 2)
    private BigDecimal valorDesconto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrcamento status;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.dataEmissao = LocalDate.now();
        if (this.dataValidade == null) {
            this.dataValidade = LocalDate.now().plusDays(30);
        }
        calcularValorTotal();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        if (this.valorMaoObra != null) {
            subtotal = subtotal.add(this.valorMaoObra);
        }
        if (this.valorMateriais != null) {
            subtotal = subtotal.add(this.valorMateriais);
        }
        if (this.valorAdicional != null) {
            subtotal = subtotal.add(this.valorAdicional);
        }

        if (this.percentualDesconto != null && this.percentualDesconto.compareTo(BigDecimal.ZERO) > 0) {
            this.valorDesconto = subtotal.multiply(this.percentualDesconto).divide(new BigDecimal(100));
            this.valorTotal = subtotal.subtract(this.valorDesconto);
        } else {
            this.valorDesconto = BigDecimal.ZERO;
            this.valorTotal = subtotal;
        }
    }

    public enum StatusOrcamento {
        RASCUNHO,
        ENVIADO,
        APROVADO,
        REJEITADO,
        CANCELADO
    }

}
