package com.example.plenamente.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private Integer id;
    private Long rut;
    private String dv_rut;
    private String p_nombre;
    private String s_nombre;
    private String p_apellido;
    private String s_apellido;
    private String correo;
    private Integer telefono;
    private String direccion;
    private Integer previsionId;
    private String previsionNombre;

}
