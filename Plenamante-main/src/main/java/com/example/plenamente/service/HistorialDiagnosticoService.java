package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.HistorialDiagnosticoDTO;
import com.example.plenamente.model.HistorialDiagnostico;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistorialDiagnosticoService {




    //convertir a dto
    private HistorialDiagnosticoDTO convertirADTO(HistorialDiagnostico historial) {
    HistorialDiagnosticoDTO dto = new HistorialDiagnosticoDTO();
    dto.setId(historial.getId());

    if (historial.getFecha() != null) {
        dto.setFecha(historial.getFecha().toString());
    }

    dto.setObservacion(historial.getObservacion());
    
    if (historial.getPaciente() != null) {
        dto.setPacienteId(historial.getPaciente().getId());
    }
    if (historial.getDiagnostico() != null) {
        dto.setDiagnosticoId(String.valueOf(historial.getDiagnostico().getId()));
    }
    
    return dto;
}

}
