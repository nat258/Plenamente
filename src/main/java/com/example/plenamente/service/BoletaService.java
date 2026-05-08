package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.model.Boleta;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {




    //convertir a dto
    private BoletaDTO convertirADTO(Boleta boleta) {
    BoletaDTO dto = new BoletaDTO();
    dto.setId(boleta.getId());
    dto.setTipoPago(boleta.getTipoPago());
    dto.setMonto(boleta.getMonto());
    
    if (boleta.getReservaHora() != null) {
        dto.setReservaHoraId(boleta.getReservaHora().getId());
    }
    return dto;
}

}