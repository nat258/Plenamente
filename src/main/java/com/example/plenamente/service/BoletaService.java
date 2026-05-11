package com.example.plenamente.service;
<<<<<<< HEAD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.model.Boleta;
import com.example.plenamente.repository.BoletaRepository;

=======
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.model.Boleta;
import com.example.plenamente.model.ReservaHora;
import com.example.plenamente.repository.BoletaRepository;
import com.example.plenamente.repository.ReservaHoraRepository;
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {
<<<<<<< HEAD
@Autowired
    private BoletaRepository boletaRepository;

=======

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ReservaHoraRepository reservaHoraRepository;

>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
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

<<<<<<< HEAD
    public String eliminar(Integer id){
        try{
            Boleta boleta = boletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No es posible eliminar boleta con ID" + id +"No existe"));
            boletaRepository.delete(boleta);
            return"La boleta'" + boleta.getId()+"'ha sido eliminada exitosamente";
        }catch(RuntimeException b){
            return b.getMessage();
        }
    }

    public Boleta guardarBoleta(Boleta boleta){
        return boletaRepository.save(boleta);
    }

    public Boleta  actualizarBoleta(Integer id, Boleta boleta){
        Boleta bole = boletaRepository.findById(id).orElseThrow(() -> new RuntimeException("La boleta no existe en los registros"));
        if(boleta.getTipoPago() != null){
            bole.setTipoPago(boleta.getTipoPago());
        }
        if(boleta.getMonto() != null){
            bole.setMonto(boleta.getMonto());
        }
        if(boleta.getReservaHora()!= null){
            bole.setReservaHora(boleta.getReservaHora());
        }
        return boletaRepository.save(bole);
    }

    //Busqueda por tipo de pago
    public List<BoletaDTO>BuscarPorTipoPago(String pago){
        return boletaRepository.findByTipoPago(pago).stream()
                .map(this::convertirADTO)
                .toList();
    }

    //Busqueda por monto exacto .
    public List<BoletaDTO>BuscarMontoExacto(Integer monto){
        return boletaRepository.findByMonto(monto).stream()
=======
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
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
                .map(this::convertirADTO)
                .toList();
    }
    
<<<<<<< HEAD
    //Busqueda por monto superior
    public List<BoletaDTO>porMontoSuperior(Integer nivelMinimo){
        return boletaRepository.findByPorMontoSuperior(nivelMinimo).stream()
                .map(this::convertirADTO)
                .toList();
    }

=======
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
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
