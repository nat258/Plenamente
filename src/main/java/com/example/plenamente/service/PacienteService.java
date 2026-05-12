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

    

    public String eliminarPacientePorRut(Long rut) {
        try {
            Paciente paciente = pacienteRepositary.findByRut(rut)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el RUT: " + rut));
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
        return pacienteRepositary.save(paciente);
    }


    public Paciente actualizarPacientePorRut(Long rut, Paciente datosNuevos) {
        Paciente pacienteExistente = pacienteRepositary.findByRut(rut)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se puede actualizar: RUT " + rut + " no encontrado."));

        validarPaciente(datosNuevos);

        pacienteExistente.setRut(datosNuevos.getRut());
        pacienteExistente.setPNombre(datosNuevos.getPNombre());
        pacienteExistente.setPApellido(datosNuevos.getPApellido());
        pacienteExistente.setCorreo(datosNuevos.getCorreo());
        pacienteExistente.setPrevision(datosNuevos.getPrevision());

        return pacienteRepositary.save(pacienteExistente);
    }


    //DTO

    public List<PacienteDTO> obtenerTodosLosPacientes(){
            return pacienteRepositary.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
        }


    public PacienteDTO buscarPorRut(Long rut) {
        Paciente paciente = pacienteRepositary.findByRut(rut)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el RUT: " + rut));
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
        dto.setP_nombre(paciente.getPNombre());
        dto.setP_apellido(paciente.getPApellido());
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
        if (paciente.getPNombre() == null || paciente.getPNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }

        // Validación para String (Correo)
        if (paciente.getCorreo() == null || !paciente.getCorreo().contains("@")) {
            throw new RuntimeException("El formato del correo electrónico no es válido.");
        }
    }

}