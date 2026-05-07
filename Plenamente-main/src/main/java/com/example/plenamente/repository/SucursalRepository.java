package com.example.plenamente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.plenamente.model.Psicologo;
import com.example.plenamente.model.Sucursal;
<<<<<<< HEAD


=======
>>>>>>> 6d0dd7193fe4b2db7b3f160bf9c7c7799edb7828

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    // Buscar por nombre
    List<Sucursal> findByNombreContainingIgnoreCase(String nombre);
    

    //psicologos de la sucursal
    @Query("SELECT p FROM Sucursal s JOIN s.psicologos p WHERE s.id = :idSucursal")
    List<Psicologo> buscarPsicologosPorSucursal(@Param("idSucursal") Integer idSucursal);

}
