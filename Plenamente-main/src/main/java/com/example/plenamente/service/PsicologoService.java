package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.PsicologoDTO;
import com.example.plenamente.model.Psicologo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PsicologoService {




    //convertir a dto
    private PsicologoDTO convertirADTO(Psicologo psicologo) {
    PsicologoDTO dto = new PsicologoDTO();
    dto.setId(psicologo.getId());
    dto.setP_nombre(psicologo.getP_nombre());
    dto.setP_apellido(psicologo.getP_apellido());
    
    // Mapeo de listas (Especialidades)
    if (psicologo.getEspecialidades() != null) {
        dto.setNombresEspecialidades(psicologo.getEspecialidades().stream()
            .map(e -> e.getNombre()).toList());
    }
    return dto;
}

}
