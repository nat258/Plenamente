package com.example.plenamente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.PsicologoDTO;
import com.example.plenamente.model.Psicologo;
<<<<<<< HEAD
import com.example.plenamente.repository.PsicologoRepositary;
=======
import com.example.plenamente.repository.PsicologoRepository;
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PsicologoService {

    @Autowired
<<<<<<< HEAD
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
=======
    private PsicologoRepository psicologoRepositary;

    public String eliminarPsicologo(Integer id) {
        try {
        Psicologo psicologo = psicologoRepositary.findById(id)
                    .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado con el ID: " + id));
            psicologoRepositary.delete(psicologo);
            return "Psicólogo eliminado con éxito";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

    public Psicologo guardarPsicologo(Psicologo psicologo) {
        validarPsicologo(psicologo);
        
        if (psicologo.getRut() != null) {
<<<<<<< HEAD
            List<Psicologo> psicologoExistente = psicologoRepositary.findByRut(psicologo.getRut());
            if (!psicologoExistente.isEmpty()) {
=======
            List<Psicologo> existentes = psicologoRepositary.findByRut(psicologo.getRut());
            if (!existentes.isEmpty()) {
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
                throw new RuntimeException("Ya existe un psicólogo registrado con el RUT: " + psicologo.getRut());
            }
        }
        
        return psicologoRepositary.save(psicologo);
    }

<<<<<<< HEAD
    public Psicologo actualizarPsicologo(Long rut, Psicologo psicologoActualizado) {
        List<Psicologo> lista = psicologoRepositary.findByRut(rut);
        if (lista.isEmpty()) {
            throw new RuntimeException("Psicólogo no encontrado con el RUT: " + rut);
        }
        Psicologo psicologoExistente = lista.get(0);
=======
    public Psicologo actualizarPsicologo(Integer id, Psicologo psicologoActualizado) {
        Psicologo psicologoExistente = psicologoRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado con el ID: " + id));
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

        validarPsicologo(psicologoActualizado);

        psicologoExistente.setRut(psicologoActualizado.getRut());
        psicologoExistente.setPNombre(psicologoActualizado.getPNombre());
        psicologoExistente.setPApellido(psicologoActualizado.getPApellido());
        psicologoExistente.setEspecialidades(psicologoActualizado.getEspecialidades());

        return psicologoRepositary.save(psicologoExistente);
    }


    //DTO

    public List<PsicologoDTO> obtenerTodosLosPsicologos(){
        return psicologoRepositary.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

<<<<<<< HEAD
    public PsicologoDTO buscarPsicologoPorRut(Long rut){
        List<Psicologo> lista = psicologoRepositary.findByRut(rut);
        if (lista.isEmpty()) {
            throw new RuntimeException("Psicólogo no encontrado con RUT: " + rut);
        }
        return convertirADTO(lista.get(0));
=======
    public PsicologoDTO buscarPsicologoPorId(Integer id){
        Psicologo psicologo = psicologoRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado con ID: " + id));
        return convertirADTO(psicologo);
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
    }

    //convertir a dto
    private PsicologoDTO convertirADTO(Psicologo psicologo) {
    PsicologoDTO dto = new PsicologoDTO();
    dto.setId(psicologo.getId());
    dto.setP_nombre(psicologo.getPNombre());
    dto.setP_apellido(psicologo.getPApellido());
    
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
        if (psicologo.getPNombre() == null || psicologo.getPNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }
        if (psicologo.getPApellido() == null || psicologo.getPApellido().trim().isEmpty()) {
            throw new RuntimeException("El apellido es obligatorio.");
        }
        // Validación opcional: verificar que tenga al menos una especialidad
        if (psicologo.getEspecialidades() == null || psicologo.getEspecialidades().isEmpty()) {
            throw new RuntimeException("El psicólogo debe tener al menos una especialidad asignada.");
        }
    }

}
