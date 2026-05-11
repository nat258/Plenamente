package com.example.plenamente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plenamente.model.Prevision;


@Repository
public interface PrevisionRepositary extends JpaRepository<Prevision, Integer> {

    List<Prevision> findByTipo(String tipo);

    //buscar prevenssion por nombre
    Prevision findByTipoContainingIgnoreCase(String nombre);


}
