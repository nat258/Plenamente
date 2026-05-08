package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.DTO.ComunaDTO;
import com.example.plenamente.model.Boleta;
import com.example.plenamente.model.Comuna;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {



    public ComunaDTO buscarPorId(Integer id){
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡Comuna no encontrada!"));
        return convertirADTO(comuna);
    }






    //convertir dto
    private ComunaDTO convertirADTO(Comuna comuna) {
    ComunaDTO dto = new ComunaDTO();
    dto.setId(comuna.getId());
    dto.setNombre(comuna.getNombre());
    
    if (comuna.getRegion() != null) {
        dto.setNombreRegion(comuna.getRegion().getNombre());
    }
    return dto;
    }
}