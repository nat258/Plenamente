package com.example.plenamente.plenamente.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El RUT es obligatorio!")
    @Min(value = 1000000, message = "El RUT debe contener al menos 7 caracteres!")
    @Max(value = 99999999, message = "El RUT debe contener máximo 8 caracteres!")
    private Long rut;

    @NotBlank(message = "El digito verificador del RUT es oblgatorio!")
    @Size(min = 1, max = 1, message = "El digito verificador del RUT debe ser solo 1 caracter!")
    private String dv_rut;

    @NotBlank(message = "El primer nombre es obligatorio!")
    @Size(min = 3, message = "El primer nombre debe contener al menos 3 caracteres!")
    private String p_nombre;

    @Size(min = 3, message = "El segundo nombre debe contener al menos 3 caracteres!")
    private String s_nombre;

    @NotBlank(message = "El primer apellido es obligatorio!")
    @Size(min = 3, message = "El primer apellido debe contener al menos 3 caracteres!")
    private String p_apellido;

    @NotBlank(message = "El segundo apellido es obligatorio!")
    @Size(min = 3, message = "El segundo apellido debe contener al menos 3 caracteres!")
    private String s_apellido;

    @Email(message = "Debe ingresar un correo electronico valido! ejemplo@example.com")
    @NotBlank(message = "El correo electronico es obligatorio!")
    private String correo;

    @NotNull(message = "El numero telefonico es obligatorio!")
    @Min(value = 900000000, message = "El numero telefonico debe contener 9 caracteres!)")
    @Max(value = 999999999, message = "El numero telefonico debe contener 9 caracteres!)")
    private Integer numero;

    @NotBlank(message = "La direccion es obligatoria!")
    private String direccion;

    @OneToOne
    @JoinColumn(name = "historialDiagnostico_id")
    private HistorialDiagnostico historialDiagnostico;

    @OneToMany(mappedBy = "paciente")
    private List<ReservaHora> reservas;

    @ManyToOne
    @JoinColumn(name = "prevension_id")
    private Prevension prevension;

}
