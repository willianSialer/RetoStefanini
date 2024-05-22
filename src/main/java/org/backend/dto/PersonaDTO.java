package org.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonaDTO {

    private String nombre;
    private String apellido;
    private String dni;
    private LocalDateTime fechaNacimiento;

}
