package com.example.plenamente.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialDiagnosticoDTO {

    private Integer id;
    private String fecha;
    private String observacion;
    private String diagnosticoId;
    private Integer pacienteId;

}
