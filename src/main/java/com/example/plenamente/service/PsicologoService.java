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

    public String eliminarPsicologo(Long rut) {
    try {
        List<Psicologo> lista = psicologoRepositary.findByRut(rut);
        
        if (lista.isEmpty()) {
            throw new RuntimeException("Psicólogo no encontrado con el RUT: " + rut);
        }

        Psicologo psicologo = lista.get(0); // Tomamos el primero de la lista
        psicologoRepositary.delete(psicologo);
        
        return "Psicólogo eliminado con éxito";
    } catch (Exception e) {
        return e.getMessage();
    }
}

    public Psicologo guardarPsicologo(Psicologo psicologo) {
        validarPsicologo(psicologo);
        
        if (psicologo.getRut() != null) {
            List<Psicologo> psicologoExistente = psicologoRepositary.findByRut(psicologo.getRut());
            if (!psicologoExistente.isEmpty()) {
                throw new RuntimeException("Ya existe un psicólogo registrado con el RUT: " + psicologo.getRut());
            }
        }
        
        return psicologoRepositary.save(psicologo);
    }

    public Psicologo actualizarPsicologo(Long rut, Psicologo psicologoActualizado) {
        List<Psicologo> lista = psicologoRepositary.findByRut(rut);
        if (lista.isEmpty()) {
            throw new RuntimeException("Psicólogo no encontrado con el RUT: " + rut);
        }
        Psicologo psicologoExistente = lista.get(0);

        validarPsicologo(psicologoActualizado);

        psicologoExistente.setRut(psicologoActualizado.getRut());
        psicologoExistente.setP_nombre(psicologoActualizado.getP_nombre());
        psicologoExistente.setP_apellido(psicologoActualizado.getP_apellido());
        psicologoExistente.setEspecialidades(psicologoActualizado.getEspecialidades());

        return psicologoRepositary.save(psicologoExistente);
    }


    //DTO

    public List<PsicologoDTO> obtenerTodosLosPsicologos(){
        return psicologoRepositary.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PsicologoDTO buscarPsicologoPorRut(Long rut){
        List<Psicologo> lista = psicologoRepositary.findByRut(rut);
        if (lista.isEmpty()) {
            throw new RuntimeException("Psicólogo no encontrado con RUT: " + rut);
        }
        return convertirADTO(lista.get(0));
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

    private void validarPsicologo(Psicologo psicologo) {
        if (psicologo.getRut() == null || psicologo.getRut() <= 0) {
            throw new RuntimeException("El RUT es obligatorio para el registro profesional.");
        }
        if (psicologo.getP_nombre() == null || psicologo.getP_nombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }
        if (psicologo.getP_apellido() == null || psicologo.getP_apellido().trim().isEmpty()) {
            throw new RuntimeException("El apellido es obligatorio.");
        }
        // Validación opcional: verificar que tenga al menos una especialidad
        if (psicologo.getEspecialidades() == null || psicologo.getEspecialidades().isEmpty()) {
            throw new RuntimeException("El psicólogo debe tener al menos una especialidad asignada.");
        }
    }

}
