package com.example.plenamente.plenamente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plenamente.plenamente.model.Prevension;

@Repository
public interface PrevensionRepository extends JpaRepository<Prevension, Integer> {

    //buscar prevenssion por nombre
    Prevension findByTipoContainingIgnoreCase(String nombre);

}
