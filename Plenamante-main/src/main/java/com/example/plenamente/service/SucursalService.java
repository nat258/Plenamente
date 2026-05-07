package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.SucursalDTO;
import com.example.plenamente.model.Sucursal;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SucursalService {



    //convertir a dto
    private SucursalDTO convertirADTO(Sucursal sucursal) {
    SucursalDTO dto = new SucursalDTO();
    dto.setId(sucursal.getId());
    dto.setNombre(sucursal.getNombre());
    dto.setDireccion(sucursal.getDireccion());
    
    if (sucursal.getComuna() != null) {
        dto.setComunaId(sucursal.getComuna().getId());
        dto.setComunaNombre(sucursal.getComuna().getNombre());
    }
    return dto;
}

}
