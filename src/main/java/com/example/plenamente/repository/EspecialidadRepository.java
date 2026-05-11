package com.example.plenamente.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.plenamente.model.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad,Integer>{

    //Busqueda de especialidad por coincidencia parcial ( ignora mayusculas y minusculas)
    List<Especialidad> findByNombreContainingIgnoreCase(String nombre);

    //Buscar todas las especialidades que tiene un Psicologo especifico 
    List<Especialidad> findByPsicologosId(Integer idPsicologo);

<<<<<<< HEAD
    


=======
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
}
