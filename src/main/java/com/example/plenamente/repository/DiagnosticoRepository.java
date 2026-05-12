package com.example.plenamente.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.plenamente.model.Diagnostico;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico,Integer> {
    
    //Buscar diagnósticos que contengan una palabra clave en el nombre
    List<Diagnostico> findByNombreContainingIgnoreCase(String nombre);
    // Buscar por nombre exacto (útil para validaciones)
    Optional<Diagnostico> findByNombreIgnoreCase(String nombre);

}
