package com.example.plenamente.service;

import java.util.List;

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
    private EspecialidadRepository especialidadRepository;

<<<<<<< HEAD
    public String eliminarEspecialidad(Integer id) {
        try {
        Especialidad especialidad = especialidadRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con el ID: " + id));
            especialidadRepository.delete(especialidad);
            return "Especialidad eliminada con éxito";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad) {
        validarEspecialidad(especialidad);
        return especialidadRepository.save(especialidad);
    }

    public Especialidad actualizarEspecialidad(Integer id, Especialidad especialidadActualizada) {
        Especialidad especialidadExistente = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con el ID: " + id));

        validarEspecialidad(especialidadActualizada);

        especialidadExistente.setNombre(especialidadActualizada.getNombre());

        return especialidadRepository.save(especialidadExistente);
    }


    // Buscar por coincidencia parcial al tipo de especialidad .
    public List<EspecialidadDTO> buscarPorNombreParcial(String nombre) {
    return especialidadRepository.findByNombreContainingIgnoreCase(nombre).stream()
            .map(this::convertirADTO)
            .toList();
=======
    // Buscar por coincidencia parcial al tipo de especialidad .
    public List<EspecialidadDTO> buscarPorNombreParcial(String nombre) {
        List<Especialidad> especialidades = especialidadRepository.findByNombreContainingIgnoreCase(nombre);
        if(especialidades.isEmpty()){
            throw new RuntimeException("No se encontraron especialidades que contengan: " + nombre);
        }
        return especialidades.stream()
                .map(this::convertirADTO)
                .toList();
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
    }

    //Buscar por Id de especialidad.
    public EspecialidadDTO buscarPorId(Integer id) {
    Especialidad especialidad = especialidadRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Especialidad no encontrada!"));

    return convertirADTO(especialidad);
    }

<<<<<<< HEAD
=======
    //Buscar todas las especialidades que tiene un psicologo 
    public List<EspecialidadDTO> buscarPorPsicologo(Integer idPsicologo) {
        List<Especialidad> especialidades = especialidadRepository.findByPsicologosId(idPsicologo);
        if (especialidades.isEmpty()) {
            throw new RuntimeException("No se encontraron especialidades para el psicólogo con ID: " + idPsicologo);
        }
        return especialidades.stream()
                .map(this::convertirADTO)
                .toList();
    }

>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
    //Convertir a DTO
    private EspecialidadDTO convertirADTO(Especialidad especialidad) {
        EspecialidadDTO especialidadDTO = new EspecialidadDTO();
        especialidadDTO.setId(especialidad.getId());
        especialidadDTO.setNombre(especialidad.getNombre());
        return especialidadDTO;
    }


<<<<<<< HEAD
    private void validarEspecialidad(Especialidad especialidad) {
        if (especialidad.getNombre() == null || especialidad.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre de la especialidad es obligatorio.");
        }
    }
=======
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

}
