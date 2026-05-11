package com.example.plenamente.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.plenamente.model.ReservaHora;

@Repository
public interface ReservaHoraRepository extends JpaRepository<ReservaHora, Integer> {

    List<ReservaHora> findByPsicologoIdAndFechaHora(Integer psicologoId, LocalDateTime fechaHora);

    //busca las reservas del cliente por el rut
    @Query("SELECT r FROM ReservaHora r JOIN r.paciente p WHERE p.rut = :rut")
    List<ReservaHora> buscarPorRutPaciente(@Param("rut") Long rut);

    //disponibilidad

    @Query("SELECT COUNT(r) > 0 FROM ReservaHora r WHERE r.psicologo.id = :idPsico AND r.fechaHora = :fechaHora")
    boolean existeCita(@Param("idPsico") Integer idPsico,
                        @Param("fechaHora") LocalDateTime fechaHora);

}
