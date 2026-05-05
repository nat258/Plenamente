package com.example.plenamente.plenamente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plenamente.plenamente.model.Region;

@Repository
public interface RegionRepositary extends JpaRepository<Region, Integer> {

    //buscar region por nombre
    Region findByNombreContainingIgnoreCase(String nombre);

}
