package com.example.plenamente.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.plenamente.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna,Integer> {
    
    // Busqueda de comuna por nombre exacto
    List<Comuna> findByNombre(String nombre);
  
    //Buscar comuna que contenga parte del nombre
    List<Comuna> findByNombreContainingIgnoreCase(String nombre);

    // Devuelve true si el nombre ya existe en la DB
    boolean existsByNombre(String nombre);

}
