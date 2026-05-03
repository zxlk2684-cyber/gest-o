package com.serviceflow.repository;

import com.serviceflow.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByClienteId(Long clienteId);

    List<Projeto> findByStatus(Projeto.StatusProjeto status);

    @Query("SELECT p FROM Projeto p WHERE p.dataInicio BETWEEN :dataInicio AND :dataFim")
    List<Projeto> findProjetosEntreDatas(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT p FROM Projeto p WHERE p.cliente.id = :clienteId AND p.status = :status")
    List<Projeto> findByClienteIdAndStatus(@Param("clienteId") Long clienteId, @Param("status") Projeto.StatusProjeto status);

    @Query("SELECT p FROM Projeto p WHERE p.titulo LIKE %:termo% OR p.descricao LIKE %:termo%")
    List<Projeto> buscarPorTermo(@Param("termo") String termo);

}
