package com.example.plenamente.plenamente.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.plenamente.plenamente.model.ReservaHora;

@Repository
public interface ReservaHoraRepositary extends JpaRepository<ReservaHora, Integer> {

    //busca las reservas del cliente por el rut
    @Query("SELECT r FROM ReservaHora r JOIN r.paciente p WHERE p.rut = :rut")
    List<ReservaHora> buscarPorRutPaciente(@Param("rut") Long rut);

    //disponibilidad
    @Query("SELECT COUNT(r) > 0 FROM ReservaHora r WHERE r.psicologo.id = :idPsico AND r.fecha = :fecha AND r.hora = :hora")
    boolean existeCita(@Param("idPsico") Integer idPsico,
                        @Param("fecha") LocalDate fecha,
                        @Param("hora") LocalTime hora);

}
