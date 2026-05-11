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

import com.example.plenamente.DTO.PrevisionDTO;
import com.example.plenamente.model.Prevision;
import com.example.plenamente.service.PrevisionService;

@RestController
@RequestMapping("/api/v1/previsiones")
public class PrevisionController {

    @Autowired
    private PrevisionService previsionService;

    public ResponseEntity<List<PrevisionDTO>> obtenerTodas() {
        List<PrevisionDTO> lista = previsionService.obtenerTodosLasPrevisiones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        try {
            PrevisionDTO dto = previsionService.buscarPrevisionPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> guardar(@RequestBody Prevision prevision) {
        try {
            Prevision guardada = previsionService.guardarPrevision(prevision);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Prevision prevision) {
        try {
            return ResponseEntity.ok(previsionService.actualizarPrevision(id, prevision));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        try {
            previsionService.eliminarPrevision(id);
            return new ResponseEntity<>("Previsión eliminada exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}