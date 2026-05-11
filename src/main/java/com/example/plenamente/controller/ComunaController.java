package com.example.plenamente.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plenamente.DTO.ComunaDTO;
import com.example.plenamente.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    //Busqueda por Id 
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            ComunaDTO comunaDTO = comunaService.buscarPorId(id);
            return new ResponseEntity<>(comunaDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Busqueda por Nombre.
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        try {
            // Como tu service devuelve una List<ComunaDTO>, aquí enviamos la lista completa
            List<ComunaDTO> listaComunas = comunaService.buscarPorNombre(nombre);
            return new ResponseEntity<>(listaComunas, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Si el service lanza la excepción cuando la lista está vacía
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Busqueda por palabras que contenga el nombre 
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombreParcial(@PathVariable String nombre) {
        try {
            List<ComunaDTO> listaResultados = comunaService.buscarPorNombreParcial(nombre);
            return new ResponseEntity<>(listaResultados, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




}
