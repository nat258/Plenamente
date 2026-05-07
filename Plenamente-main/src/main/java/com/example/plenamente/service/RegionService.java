package com.example.plenamente.service;

import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.RegionDTO;
import com.example.plenamente.model.Region;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    //convertir a dto
    private RegionDTO convertirADTO(Region region) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(region.getId());
        regionDTO.setNombre(region.getNombre());
        return regionDTO;

}
}