package com.example.plenamente.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.plenamente.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer>{

    List<Paciente> findByRut(Long rut);

    //Buscar por nombre y apellido
    List<Paciente> findByPNombreContainingIgnoreCaseOrPApellidoContainingIgnoreCase(String p_nombre, String p_apellido);
    //Busqueda por Rut 
    Optional<Paciente> findByRut(String rut);
    //Verificar si rut esta registrado 
    boolean existsByRut(String rut);

}
