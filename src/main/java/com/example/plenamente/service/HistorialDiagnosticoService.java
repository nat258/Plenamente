package com.example.plenamente.service;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
import org.springframework.stereotype.Service;

import com.example.plenamente.DTO.HistorialDiagnosticoDTO;
import com.example.plenamente.model.HistorialDiagnostico;
<<<<<<< HEAD
=======
import com.example.plenamente.repository.HistorialDiagnosticoRepository;

>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistorialDiagnosticoService {

<<<<<<< HEAD


=======
    @Autowired
    private HistorialDiagnosticoRepository historialDiagnosticoRepository;
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)

    //convertir a dto
    private HistorialDiagnosticoDTO convertirADTO(HistorialDiagnostico historialDiagnostico) {
        HistorialDiagnosticoDTO historialDiagnosticoDTO = new HistorialDiagnosticoDTO();
        historialDiagnosticoDTO.setId(historialDiagnostico.getId());
        historialDiagnosticoDTO.setFecha(historialDiagnostico.getFecha());
        return historialDiagnosticoDTO;
    }

}
