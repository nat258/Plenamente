package com.example.plenamente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.ReservaHoraDTO;
import com.example.plenamente.model.ReservaHora;
import com.example.plenamente.repository.ReservaHoraRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaHoraService {

    @Autowired
<<<<<<< HEAD
    private ReservaHoraRepository reservaHoraRepositary;
=======
    private ReservaHoraRepository reservaHoraRepository;
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

    //cancelar cita
    public String eliminarReserva(Integer id) {
        try {
<<<<<<< HEAD
        ReservaHora reserva = reservaHoraRepositary.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el ID: " + id));
            reservaHoraRepositary.delete(reserva);
=======
        ReservaHora reserva = reservaHoraRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el ID: " + id));
            reservaHoraRepository.delete(reserva);
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
            return "Reserva eliminada con éxito";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //agendar reserva
    public ReservaHora guardarReserva(ReservaHora reserva) {
        validarReserva(reserva);
<<<<<<< HEAD
        List<ReservaHora> citasConflictivas = reservaHoraRepositary.findByPsicologoIdAndFechaHora(
=======
        List<ReservaHora> citasConflictivas = reservaHoraRepository.findByPsicologoIdAndFechaHora(
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
            reserva.getPsicologo().getId(),
            reserva.getFechaHora()
        );

        if (!citasConflictivas.isEmpty()) {
            throw new RuntimeException("El psicólogo ya tiene una reserva para este horario.");
        }

        if (reserva.getEstado() == null) {
            reserva.setEstado("PENDIENTE");
        }

<<<<<<< HEAD
        return reservaHoraRepositary.save(reserva);
=======
        return reservaHoraRepository.save(reserva);
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
    }


    //actualizar reserva
    public ReservaHora actualizarReserva(Integer id, ReservaHora reservaActualizada) {
<<<<<<< HEAD
        ReservaHora reservaExistente = reservaHoraRepositary.findById(id)
=======
        ReservaHora reservaExistente = reservaHoraRepository.findById(id)
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el ID: " + id));

        //cambiar fecha y hora
        if (!reservaExistente.getFechaHora().equals(reservaActualizada.getFechaHora())) {
<<<<<<< HEAD
            List<ReservaHora> choques = reservaHoraRepositary.findByPsicologoIdAndFechaHora(
=======
            List<ReservaHora> choques = reservaHoraRepository.findByPsicologoIdAndFechaHora(
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
                reservaExistente.getPsicologo().getId(),
                reservaActualizada.getFechaHora()
            );
            if (!choques.isEmpty()) {
                throw new RuntimeException("No se puede cambiar la cita: El nuevo horario ya está ocupado.");
            }
        }

        reservaExistente.setFechaHora(reservaActualizada.getFechaHora());
        reservaExistente.setEstado(reservaActualizada.getEstado());
        
<<<<<<< HEAD
        return reservaHoraRepositary.save(reservaExistente);
=======
        return reservaHoraRepository.save(reservaExistente);
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
    }


    //DTO

    public List<ReservaHoraDTO> obtenerTodosLasReservas(){
<<<<<<< HEAD
        return reservaHoraRepositary.findAll().stream()
=======
        return reservaHoraRepository.findAll().stream()
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
                .map(this::convertirADTO)
                .toList();
    }

    public ReservaHoraDTO buscarReservaHoraPorId(Integer id){
<<<<<<< HEAD
        ReservaHora reserva = reservaHoraRepositary.findById(id)
=======
        ReservaHora reserva = reservaHoraRepository.findById(id)
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el ID: " + id));
        return convertirADTO(reserva);
    }

    //convertir a dto
    private ReservaHoraDTO convertirADTO(ReservaHora reserva) {
    ReservaHoraDTO dto = new ReservaHoraDTO();
    dto.setId(reserva.getId());
    dto.setFechaHora(reserva.getFechaHora());
    dto.setEstado(reserva.getEstado());
    
    if (reserva.getPaciente() != null) {
        dto.setPacienteId(reserva.getPaciente().getId());
        dto.setPacienteNombre(reserva.getPaciente().getP_nombre() + " " + reserva.getPaciente().getP_apellido());
    }
    if (reserva.getPsicologo() != null) {
        dto.setPsicologoId(reserva.getPsicologo().getId());
        dto.setPsicologoNombre(reserva.getPsicologo().getP_nombre() + " " + reserva.getPsicologo().getP_apellido());
    }
    return dto;
<<<<<<< HEAD
}
=======
    }
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)


    //VALIDACION FECHA Y HORA
    private void validarReserva(ReservaHora reserva) {
            if (reserva.getPaciente() == null || reserva.getPsicologo() == null) {
                throw new RuntimeException("La reserva debe incluir un paciente y un psicólogo.");
            }
            if (reserva.getFechaHora() == null) {
                throw new RuntimeException("La fecha y hora son obligatorias.");
            }
        }

<<<<<<< HEAD
}
=======
    
    

    //Permite eliminar una boleta de reserva realizada 
    public String eliminarBoletaDeReserva(Integer reservaId) {
            // Buscar la reserva
            ReservaHora reserva = reservaHoraRepository.findById(reservaId)
                    .orElseThrow(() -> new RuntimeException("No se encontró la reserva con ID: " + reservaId));

            //Verificar si existe la boleta
            if (reserva.getBoleta() == null) {
                throw new RuntimeException("Esta reserva no tiene ninguna boleta asociada.");
            }

            //Eliminar la relación
            reserva.setBoleta(null);
            reservaHoraRepository.save(reserva);

            return "La boleta ha sido eliminada de la reserva " + reservaId;
        }
    
    
}
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
