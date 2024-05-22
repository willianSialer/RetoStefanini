package org.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 70)
    private String apellido;

    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    @Column(name="fecha_nacimiento", nullable = false)
    private LocalDateTime fechaNacimiento;

}
