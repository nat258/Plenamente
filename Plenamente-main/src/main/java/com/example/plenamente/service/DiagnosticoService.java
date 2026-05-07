package com.example.plenamente.service;

import com.example.plenamente.DTO.DiagnosticoDTO;
import com.example.plenamente.model.Diagnostico;

import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Transactional
public class DiagnosticoService {


    //convertir a dto
    public DiagnosticoDTO convertirADTO(Diagnostico diagnostico) {
        DiagnosticoDTO diagnosticoDTO = new DiagnosticoDTO();
        diagnosticoDTO.setId(diagnostico.getId());
        diagnosticoDTO.setDescripcion(diagnostico.getDescripcion());
        return diagnosticoDTO;
    }
}
