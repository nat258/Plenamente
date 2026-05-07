package com.example.plenamente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.PsicologoDTO;
import com.example.plenamente.model.Psicologo;
import com.example.plenamente.repository.PsicologoRepositary;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PsicologoService {

    @Autowired
    private PsicologoRepositary psicologoRepositary;

    public List<PsicologoDTO> obtenerTodosLosPsicologos(){
        return psicologoRepositary.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PsicologoDTO buscarPsicologoPorId(Integer id){
        Psicologo psicologo = psicologoRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado con ID: " + id));
        return convertirADTO(psicologo);
    }


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
