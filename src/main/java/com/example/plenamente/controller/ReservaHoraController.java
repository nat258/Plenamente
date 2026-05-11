package com.example.plenamente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plenamente.DTO.ReservaHoraDTO;
import com.example.plenamente.model.ReservaHora;
import com.example.plenamente.service.ReservaHoraService;

@RestController
@RequestMapping("/api/v1/reserva-hora")
public class ReservaHoraController {

    @Autowired
    private ReservaHoraService reservaHoraService;

    //Obtener todas las reservas (Formato DTO)
    @GetMapping
    public ResponseEntity<List<ReservaHoraDTO>> obtenerTodas() {
        return ResponseEntity.ok(reservaHoraService.obtenerTodosLasReservas());
    }

    //Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Integer id) {
        try {
            ReservaHoraDTO dto = reservaHoraService.buscarReservaHoraPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Crear una nueva reserva
    @PostMapping
    public ResponseEntity<Object> crearReserva(@RequestBody ReservaHora reserva) {
        try {
            ReservaHora nueva = reservaHoraService.guardarReserva(reserva);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Actualizar reserva (Cambiar fecha, hora o estado)
    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarReserva(@PathVariable Integer id, @RequestBody ReservaHora reserva) {
        try {
            ReservaHora editada = reservaHoraService.actualizarReserva(id, reserva);
            return new ResponseEntity<>(editada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Eliminar (Cancelar) reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarReserva(@PathVariable Integer id) {
        try {
            reservaHoraService.eliminarReserva(id);
            return new ResponseEntity<>("Reserva eliminada exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
            
    }

    @DeleteMapping("/reserva/{reservaId}/boleta")
    public ResponseEntity<String> quitarBoleta(@PathVariable Integer reservaId) {
        try {
            String mensaje = reservaHoraService.eliminarBoletaDeReserva(reservaId);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
