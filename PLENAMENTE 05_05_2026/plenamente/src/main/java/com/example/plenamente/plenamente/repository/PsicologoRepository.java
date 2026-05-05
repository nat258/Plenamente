package com.example.plenamente.plenamente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.plenamente.plenamente.model.Psicologo;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {

    // Buscar psicólogos por especialidad
    List<Psicologo> findByEspecialidadContainingIgnoreCase(String especialidad);

    //buscar por especialidad con query
    @Query("SELECT p FROM Psicologo p JOIN p.especialidades e WHERE e.nombre = :nombreEsp")
    List<Psicologo> buscarPorEspecialidad(@Param("nombreEsp") String nombreEsp);

    // Buscar por nombre de psicologo
    List<Psicologo> findByNombreContainingIgnoreCase(String p_nombre);

}
