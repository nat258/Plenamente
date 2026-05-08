package com.example.plenamente.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.model.Boleta;
import com.example.plenamente.repository.BoletaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {
    @Autowired
    private BoletaRepository boletaRepository;

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
                .map(this::convertirADTO)
                .toList();        
    }
    
    //Busqueda por monto superior 
    public List<BoletaDTO>porMontoSuperior(Integer nivelMinimo){
        return boletaRepository.PorMontoSuperior(nivelMinimo).stream()
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
