package com.serviceflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "materiais")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private String unidade;

    @Column(name = "quantidade_necessaria", precision = 10, scale = 2, nullable = false)
    private BigDecimal quantidadeNecessaria;

    @Column(name = "quantidade_utilizada", precision = 10, scale = 2)
    private BigDecimal quantidadeUtilizada;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

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
        calcularValorTotal();
        this.quantidadeUtilizada = BigDecimal.ZERO;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        if (this.quantidadeNecessaria != null && this.precoUnitario != null) {
            this.valorTotal = this.quantidadeNecessaria.multiply(this.precoUnitario);
        }
    }

}
