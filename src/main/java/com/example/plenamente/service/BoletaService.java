package com.example.plenamente.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.model.Boleta;
import com.example.plenamente.model.ReservaHora;
import com.example.plenamente.repository.BoletaRepository;
import com.example.plenamente.repository.ReservaHoraRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ReservaHoraRepository reservaHoraRepository;

    public List<BoletaDTO>obtenerBoletas(){
        return boletaRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public BoletaDTO buscarPorId(Integer id){
        Boleta boleta = boletaRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡Boleta no encontrada!"));
        return convertirADTO(boleta);
    }

    public String eliminar(Integer id) {
    Boleta boleta = boletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No es posible eliminar la boleta con ID " + id + ". No existe."));
    boletaRepository.delete(boleta);
    return "La boleta " + id + " ha sido eliminada exitosamente.";
    }

    public BoletaDTO guardarBoleta(BoletaDTO boletaDTO){
        ReservaHora reserva = reservaHoraRepository.findById(boletaDTO.getReservaHoraId())
            .orElseThrow(() -> new RuntimeException("No se encontro la reserva con ID:" + boletaDTO.getReservaHoraId()));
        Boleta boleta = new Boleta();
        boleta.setTipoPago(boletaDTO.getTipoPago());
        boleta.setMonto(boletaDTO.getMonto());
        boleta.setReservaHora(reserva);
        reserva.setBoleta(boleta);
        Boleta boletaGuardada = boletaRepository.save(boleta);
        return convertirADTO(boletaGuardada);
    }


    //Busqueda por monto exacto .
    public List<BoletaDTO>buscarMontoExacto(Integer monto){
        List<Boleta> boletas = boletaRepository.findByMonto(monto);
        if (boletas.isEmpty()){
            throw new RuntimeException("No se encontraron boletas con el monto " + monto);
        }
        return boletas.stream()
                .map(this::convertirADTO)
                .toList();
    }
    
    //Convertir a DTO
    private BoletaDTO convertirADTO(Boleta boleta) {
    BoletaDTO dto = new BoletaDTO();
    dto.setId(boleta.getId());
    dto.setTipoPago(boleta.getTipoPago());
    dto.setMonto(boleta.getMonto());
    
    if (boleta.getReservaHora() != null) {
        dto.setReservaHoraId(boleta.getReservaHora().getId());
    }
    return dto;
    }

}
