package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.HistorialDiagnosticoDTO;
import com.example.plenamente.model.HistorialDiagnostico;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistorialDiagnosticoService {




    //convertir a dto
    private HistorialDiagnosticoDTO convertirADTO(HistorialDiagnostico historialDiagnostico) {
        HistorialDiagnosticoDTO historialDiagnosticoDTO = new HistorialDiagnosticoDTO();
        historialDiagnosticoDTO.setId(historialDiagnostico.getId());
        historialDiagnosticoDTO.setFecha(historialDiagnostico.getFecha());
        return historialDiagnosticoDTO;
    }

}
