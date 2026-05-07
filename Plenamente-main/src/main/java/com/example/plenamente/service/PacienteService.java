package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.PacienteDTO;
import com.example.plenamente.model.Paciente;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PacienteService {




    //convertir a dto
    private PacienteDTO convertirADTO(Paciente paciente) {
    PacienteDTO dto = new PacienteDTO();
    dto.setId(paciente.getId());
    dto.setRut(paciente.getRut());
    dto.setP_nombre(paciente.getP_nombre());
    dto.setP_apellido(paciente.getP_apellido());
    dto.setCorreo(paciente.getCorreo());
    
    if (paciente.getPrevision() != null) {
        dto.setPrevisionId(paciente.getPrevision().getId());
        dto.setPrevisionNombre(paciente.getPrevision().getTipo());
    }
    return dto;
}


}
