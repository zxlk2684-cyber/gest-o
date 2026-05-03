package com.serviceflow.repository;

import com.serviceflow.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByProjetoId(Long projetoId);

    List<Material> findByNomeContainingIgnoreCase(String nome);

}
