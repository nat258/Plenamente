package com.example.plenamente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.PacienteDTO;
import com.example.plenamente.model.Paciente;
import com.example.plenamente.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepositary;

    

<<<<<<< HEAD
    public String eliminarPacientePorRut(Long rut) {
        try {
            Paciente paciente = pacienteRepositary.findByRut(rut)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el RUT: " + rut));
=======
    public String eliminarPaciente(Integer id) {
        try {
            Paciente paciente = pacienteRepositary.findById(id)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el ID: " + id));
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
            pacienteRepositary.delete(paciente);
            return "Paciente eliminado con éxito";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public Paciente guardarPaciente(Paciente paciente) {
    validarPaciente(paciente);
    
    List<Paciente> listaPacientes = pacienteRepositary.findByRut(paciente.getRut());

    if (!listaPacientes.isEmpty()) {
        throw new RuntimeException("El RUT " + paciente.getRut() + " ya se encuentra registrado en el sistema.");
    }
    
    // 4. Si llegamos aquí, es porque no existe, así que guardamos
    return pacienteRepositary.save(paciente);
}

<<<<<<< HEAD

    public Paciente actualizarPacientePorRut(Long rut, Paciente datosNuevos) {
        Paciente pacienteExistente = pacienteRepositary.findByRut(rut)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se puede actualizar: RUT " + rut + " no encontrado."));

        validarPaciente(datosNuevos);

        // Actualizamos los campos (excepto el RUT si no quieres que cambie)
        pacienteExistente.setP_nombre(datosNuevos.getP_nombre());
        pacienteExistente.setP_apellido(datosNuevos.getP_apellido());
        pacienteExistente.setCorreo(datosNuevos.getCorreo());
        pacienteExistente.setPrevision(datosNuevos.getPrevision());

        return pacienteRepositary.save(pacienteExistente);
    }

=======
    

    public Paciente actualizarPaciente(Integer id, Paciente pacienteActualizado) {
    Paciente pacienteExistente = pacienteRepositary.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el ID: " + id));

    validarPaciente(pacienteActualizado);

    if (!pacienteExistente.getRut().equals(pacienteActualizado.getRut())) {
        if (!pacienteRepositary.findByRut(pacienteActualizado.getRut()).isEmpty()) {
            throw new RuntimeException("El nuevo RUT ya pertenece a otro paciente.");
        }
    }

    pacienteExistente.setRut(pacienteActualizado.getRut());
    pacienteExistente.setP_nombre(pacienteActualizado.getP_nombre());
    pacienteExistente.setP_apellido(pacienteActualizado.getP_apellido());
    pacienteExistente.setCorreo(pacienteActualizado.getCorreo());
    pacienteExistente.setPrevision(pacienteActualizado.getPrevision());

    return pacienteRepositary.save(pacienteExistente);
}

>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

    //DTO

    public List<PacienteDTO> obtenerTodosLosPacientes(){
            return pacienteRepositary.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
        }


<<<<<<< HEAD
    public PacienteDTO buscarPorRut(Long rut) {
        Paciente paciente = pacienteRepositary.findByRut(rut)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el RUT: " + rut));
=======
    public PacienteDTO buscarPacientePorId(Integer id){
        Paciente paciente = pacienteRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
        return convertirADTO(paciente);
    }

public List<PacienteDTO> buscarPacientesPorRut(Long rut) {
    return pacienteRepositary.findByRut(rut).stream()
            .map(this::convertirADTO)
            .toList();
}



    //convertir a dto
    private PacienteDTO convertirADTO(Paciente paciente) {
    PacienteDTO dto = new PacienteDTO();
    dto.setId(paciente.getId());
    dto.setRut(paciente.getRut());
    dto.setP_nombre(paciente.getP_nombre());
    dto.setP_apellido(paciente.getP_apellido());
    dto.setCorreo(paciente.getCorreo());
    
    if (paciente.getPrevision() != null) {
        dto.setPrevisionId(paciente.getPrevision().getId());
        dto.setPrevisionNombre(paciente.getPrevision().getTipo());
    }
    return dto;
}


    //validaciones
    private void validarPaciente(Paciente paciente) {
        // Validación para Long (RUT)
        if (paciente.getRut() == null || paciente.getRut() <= 0) {
            throw new RuntimeException("El RUT es obligatorio y debe ser un número válido.");
        }
        
        // Validación para String (Nombre)
        if (paciente.getP_nombre() == null || paciente.getP_nombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }

        // Validación para String (Correo)
        if (paciente.getCorreo() == null || !paciente.getCorreo().contains("@")) {
            throw new RuntimeException("El formato del correo electrónico no es válido.");
        }
    }

}