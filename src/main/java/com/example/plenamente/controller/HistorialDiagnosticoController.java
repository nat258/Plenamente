package com.example.plenamente.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.plenamente.DTO.HistorialDiagnosticoDTO;
import com.example.plenamente.model.HistorialDiagnostico;
import com.example.plenamente.service.HistorialDiagnosticoService;

@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialDiagnosticoController {

    @Autowired
    private HistorialDiagnosticoService historialService;

    @GetMapping
    public ResponseEntity<List<HistorialDiagnosticoDTO>> obtenerTodos() {
        List<HistorialDiagnosticoDTO> lista = historialService.obtenerTodo();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarHistorial(@RequestBody HistorialDiagnostico historial) {
        try {
            HistorialDiagnosticoDTO nuevo = historialService.guardarHistorial(historial);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHistorial(@PathVariable Integer id, @RequestBody HistorialDiagnostico historial) {
        try {
            HistorialDiagnosticoDTO actualizado = historialService.actualizarHistorial(id, historial);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHistorial(@PathVariable Integer id) {
        String mensaje = historialService.eliminarHistorial(id);
        if (mensaje.contains("no se encuentra registrado")) {
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (mensaje.contains("No se puede eliminar")) {
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<?> buscarPorPaciente(@PathVariable Integer id) {
        try {
            List<HistorialDiagnosticoDTO> ficha = historialService.diagnosticoPorIdPaciente(id);

            if (ficha.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ficha, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> buscarPorFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            List<HistorialDiagnosticoDTO> resultados = historialService.buscarPorFecha(fecha);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
