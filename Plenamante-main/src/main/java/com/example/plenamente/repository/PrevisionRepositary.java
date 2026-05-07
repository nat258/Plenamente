package com.example.plenamente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plenamente.model.Prevision;



@Repository
public interface PrevisionRepositary extends JpaRepository<Prevision, Integer> {

    //buscar prevenssion por nombre
    Prevision findByTipoContainingIgnoreCase(String nombre);


}
