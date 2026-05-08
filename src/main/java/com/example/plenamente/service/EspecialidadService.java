package com.example.plenamente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.EspecialidadDTO;
import com.example.plenamente.model.Especialidad;
import com.example.plenamente.repository.EspecialidadRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepositary;



    //convertir a dto
    private EspecialidadDTO convertirADTO(Especialidad especialidad) {
        EspecialidadDTO especialidadDTO = new EspecialidadDTO();
        especialidadDTO.setId(especialidad.getId());
        especialidadDTO.setNombre(especialidad.getNombre());
        return especialidadDTO;
    }

}