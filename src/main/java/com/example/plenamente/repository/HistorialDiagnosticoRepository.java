package com.example.plenamente.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.plenamente.model.HistorialDiagnostico;

@Repository
public interface HistorialDiagnosticoRepository extends JpaRepository<HistorialDiagnostico,Integer>{
    
    //Buscar todos los historiales de un paciente específico ( mostrar ficha completa del paciente)
    List<HistorialDiagnostico> findByPacienteId(Integer pacienteId);
    // Busqueda de  historiales por una fecha exacta
    List<HistorialDiagnostico> findByFecha(LocalDate fecha);

}
