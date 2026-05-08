package com.example.plenamente.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaHoraDTO {

    private Integer id;
    private LocalDate fechaHora;
    private String estado;

    private Integer pacienteId;
    private String pacienteNombre;

    private Integer psicologoId;
    private String psicologoNombre;

    private Integer boletaId;

}
