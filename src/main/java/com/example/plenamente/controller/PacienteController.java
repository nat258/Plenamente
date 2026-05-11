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

import com.example.plenamente.DTO.PacienteDTO;
import com.example.plenamente.model.Paciente;
import com.example.plenamente.service.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> obtenerTodos() {
        List<PacienteDTO> pacientes = pacienteService.obtenerTodosLosPacientes();
        if (pacientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }


    // Buscar por RUT
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Object> obtenerPorRut(@PathVariable Long rut) {
        try {
            PacienteDTO paciente = pacienteService.buscarPorRut(rut);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Guardar un nuevo paciente
    @PostMapping
    public ResponseEntity<Object> guardar(@RequestBody Paciente paciente) {
        try {
            Paciente guardado = pacienteService.guardarPaciente(paciente);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Actualizar paciente existente
    @PutMapping("/rut/{rut}")
    public ResponseEntity<Object> actualizar(@PathVariable Long rut, @RequestBody Paciente paciente) {
        try {
            Paciente actualizado = pacienteService.actualizarPacientePorRut(rut, paciente);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Eliminar paciente
    @DeleteMapping("/rut/{rut}")
    public ResponseEntity<String> eliminar(@PathVariable Long rut) {
        try {
            pacienteService.eliminarPacientePorRut(rut);
            return new ResponseEntity<>("Paciente eliminado exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
