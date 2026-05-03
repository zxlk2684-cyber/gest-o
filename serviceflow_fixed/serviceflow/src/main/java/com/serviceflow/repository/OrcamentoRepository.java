package com.serviceflow.repository;

import com.serviceflow.entity.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Optional<Orcamento> findByNumeroOrcamento(String numeroOrcamento);

    List<Orcamento> findByProjetoId(Long projetoId);

    List<Orcamento> findByStatus(Orcamento.StatusOrcamento status);

    @Query("SELECT o FROM Orcamento o WHERE o.projeto.id = :projetoId AND o.status = :status")
    List<Orcamento> findByProjetoIdAndStatus(@Param("projetoId") Long projetoId, @Param("status") Orcamento.StatusOrcamento status);

}
