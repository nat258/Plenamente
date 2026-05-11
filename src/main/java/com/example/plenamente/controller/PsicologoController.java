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

import com.example.plenamente.DTO.PsicologoDTO;
import com.example.plenamente.model.Psicologo;
import com.example.plenamente.service.PsicologoService;

@RestController
@RequestMapping("/api/v1/psicologos")
public class PsicologoController {

    @Autowired
    private PsicologoService psicologoService;

    //Obtener todos
    @GetMapping
    public ResponseEntity<List<PsicologoDTO>> obtenerTodos() {
       List<PsicologoDTO> lista = psicologoService.obtenerTodosLosPsicologos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //Buscar por RUT
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Object> obtenerPorRut(@PathVariable Long rut) {
        try {
            PsicologoDTO psicologo = psicologoService.buscarPsicologoPorRut(rut);
            return new ResponseEntity<>(psicologo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Guardar
    @PostMapping
    public ResponseEntity<Object> agregarPsicologo(@RequestBody Psicologo psicologo) {
        try {
            Psicologo guardado = psicologoService.guardarPsicologo(psicologo);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Actualizar por RUT
    @PutMapping("/rut/{rut}")
    public ResponseEntity<Object> actualizarPsicologo(@PathVariable Long rut, @RequestBody Psicologo psicologo) {
        try {
            Psicologo editado = psicologoService.actualizarPsicologo(rut, psicologo);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar por RUT
    @DeleteMapping("/rut/{rut}")
    public ResponseEntity<String> eliminarPsicologo(@PathVariable Long rut) {
        String resultado = psicologoService.eliminarPsicologo(rut);
        if (resultado.contains("éxito")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}