package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.ReservaHoraDTO;
import com.example.plenamente.model.ReservaHora;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaHoraService {



    //convertir a dto
    private ReservaHoraDTO convertirADTO(ReservaHora reserva) {
    ReservaHoraDTO dto = new ReservaHoraDTO();
    dto.setId(reserva.getId());
    dto.setFechaHora(reserva.getFechaHora());
    dto.setEstado(reserva.getEstado());
    
    if (reserva.getPaciente() != null) {
        dto.setPacienteId(reserva.getPaciente().getId());
        dto.setPacienteNombre(reserva.getPaciente().getP_nombre() + " " + reserva.getPaciente().getP_apellido());
    }
    if (reserva.getPsicologo() != null) {
        dto.setPsicologoId(reserva.getPsicologo().getId());
        dto.setPsicologoNombre(reserva.getPsicologo().getP_nombre() + " " + reserva.getPsicologo().getP_apellido());
    }
    return dto;
}

}
