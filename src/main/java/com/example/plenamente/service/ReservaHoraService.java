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
    private ReservaHoraRepository reservaHoraRepository;

    //cancelar cita
    public String eliminarReserva(Integer id) {
        try {
        ReservaHora reserva = reservaHoraRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el ID: " + id));
            reservaHoraRepository.delete(reserva);
            return "Reserva eliminada con éxito";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //agendar reserva
    public ReservaHora guardarReserva(ReservaHora reserva) {
        validarReserva(reserva);
        List<ReservaHora> citasConflictivas = reservaHoraRepository.findByPsicologoIdAndFechaHora(
            reserva.getPsicologo().getId(),
            reserva.getFechaHora()
        );

        if (!citasConflictivas.isEmpty()) {
            throw new RuntimeException("El psicólogo ya tiene una reserva para este horario.");
        }

        if (reserva.getEstado() == null) {
            reserva.setEstado("PENDIENTE");
        }

        return reservaHoraRepository.save(reserva);
    }


    //actualizar reserva
    public ReservaHora actualizarReserva(Integer id, ReservaHora reservaActualizada) {
        ReservaHora reservaExistente = reservaHoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el ID: " + id));

        //cambiar fecha y hora
        if (!reservaExistente.getFechaHora().equals(reservaActualizada.getFechaHora())) {
            List<ReservaHora> choques = reservaHoraRepository.findByPsicologoIdAndFechaHora(
                reservaExistente.getPsicologo().getId(),
                reservaActualizada.getFechaHora()
            );
            if (!choques.isEmpty()) {
                throw new RuntimeException("No se puede cambiar la cita: El nuevo horario ya está ocupado.");
            }
        }

        reservaExistente.setFechaHora(reservaActualizada.getFechaHora());
        reservaExistente.setEstado(reservaActualizada.getEstado());
        
        return reservaHoraRepository.save(reservaExistente);
    }


    //DTO

    public List<ReservaHoraDTO> obtenerTodosLasReservas(){
        return reservaHoraRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ReservaHoraDTO buscarReservaHoraPorId(Integer id){
        ReservaHora reserva = reservaHoraRepository.findById(id)
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
        dto.setPacienteNombre(reserva.getPaciente().getPNombre() + " " + reserva.getPaciente().getPApellido());
    }
    if (reserva.getPsicologo() != null) {
        dto.setPsicologoId(reserva.getPsicologo().getId());
        dto.setPsicologoNombre(reserva.getPsicologo().getPNombre() + " " + reserva.getPsicologo().getPApellido());
    }
    return dto;
    }


    //VALIDACION FECHA Y HORA
    private void validarReserva(ReservaHora reserva) {
            if (reserva.getPaciente() == null || reserva.getPsicologo() == null) {
                throw new RuntimeException("La reserva debe incluir un paciente y un psicólogo.");
            }
            if (reserva.getFechaHora() == null) {
                throw new RuntimeException("La fecha y hora son obligatorias.");
            }
    }

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
