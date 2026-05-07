package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.EspecialidadDTO;
import com.example.plenamente.model.Especialidad;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecialidadService {



    //convertir a dto
    private EspecialidadDTO convertirADTO(Especialidad especialidad) {
        EspecialidadDTO especialidadDTO = new EspecialidadDTO();
        especialidadDTO.setId(especialidad.getId());
        especialidadDTO.setNombre(especialidad.getNombre());
        return especialidadDTO;
    }

}
