package com.example.plenamente.plenamente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plenamente.plenamente.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

}
