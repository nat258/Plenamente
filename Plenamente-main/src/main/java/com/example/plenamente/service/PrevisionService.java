package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.PrevisionDTO;
import com.example.plenamente.model.Prevision;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrevisionService {



    //convertir a dto
    private PrevisionDTO convertirADTO(Prevision prevision) {
        PrevisionDTO previsionDTO = new PrevisionDTO();
        previsionDTO.setId(prevision.getId());
        previsionDTO.setTipo(prevision.getTipo());
        return previsionDTO;
    }

}
