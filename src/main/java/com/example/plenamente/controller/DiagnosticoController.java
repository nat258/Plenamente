package com.example.plenamente.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.plenamente.DTO.DiagnosticoDTO;
import com.example.plenamente.model.Diagnostico;
import com.example.plenamente.service.DiagnosticoService;

@RestController
@RequestMapping("/api/v1/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public ResponseEntity<List<DiagnosticoDTO>> obtenerTodos() {
        List<DiagnosticoDTO> diagnosticos = diagnosticoService.obtenerTodos();
        if (diagnosticos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            DiagnosticoDTO encontrado = diagnosticoService.buscarPorId(id);
            return new ResponseEntity<>(encontrado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<DiagnosticoDTO>> buscarPorPalabraClave(@PathVariable String nombre) {
        List<DiagnosticoDTO> resultados = diagnosticoService.buscarPorPalabraClave(nombre);
        if (resultados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> guardarDiagnostico(@RequestBody Diagnostico diagnostico) {
        try {
            DiagnosticoDTO nuevo = diagnosticoService.guardarDiagnostico(diagnostico);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDiagnostico(@PathVariable Integer id, @RequestBody Diagnostico diagnostico) {
        try {
            DiagnosticoDTO actualizado = diagnosticoService.actualizarDiagnostico(id, diagnostico);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDiagnostico(@PathVariable Integer id) {
        String mensaje = diagnosticoService.eliminarDiagnostico(id);
        if (mensaje.contains("no se encuentra registrado")) {
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (mensaje.contains("Error: No se puede eliminar")) {
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
