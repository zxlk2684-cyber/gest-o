package com.serviceflow.repository;

import com.serviceflow.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    List<Cliente> findByCidade(String cidade);

    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:termo% OR c.email LIKE %:termo% OR c.telefone LIKE %:termo%")
    List<Cliente> buscarPorTermo(@Param("termo") String termo);

}
