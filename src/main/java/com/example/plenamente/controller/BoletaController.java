package com.example.plenamente.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plenamente.DTO.BoletaDTO;
import com.example.plenamente.service.BoletaService;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<?>todasLasBoletas(){
        List<BoletaDTO> boletas = boletaService.obtenerBoletas();
        if(!boletas.isEmpty()){
            return new ResponseEntity<>(boletas,HttpStatus.OK);
        }
        return new  ResponseEntity<>("No hay boletas", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>boletaPorId(@PathVariable Integer id){
        try{
            BoletaDTO boleta = boletaService.buscarPorId(id);
            return new  ResponseEntity<>(boleta, HttpStatus.ACCEPTED);
        }catch(RuntimeException e){
            return new  ResponseEntity<>("No se encontro la boleta",HttpStatus.NOT_FOUND);

        }
    }

    //Eliminar boleta
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        try {
            String mensaje = boletaService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Guardar nueva Boleta
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody BoletaDTO boletaDTO) {
        try {
            BoletaDTO respuesta = boletaService.guardarBoleta(boletaDTO);
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            //si reserva no existe 
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("No existe ninguna reserva para la creacion de esta Boleta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Busqueda por monto exacto 
    @GetMapping("/monto/{monto}")
    public ResponseEntity<List<BoletaDTO>>obtenerPorMonto(@PathVariable Integer monto){
        List<BoletaDTO>dtos = boletaService.buscarMontoExacto(monto);
        return ResponseEntity.ok(dtos);
    }





}
