package com.unir.Usuarios.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotNull
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotNull
    private String apellido;

    @Column(name = "correo", unique = true, nullable = false)
    @NotNull
    @Email
    private String correo;

    @Column(name = "fechaNacimiento", nullable = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Column(name = "telefono", unique = true, nullable = false)
    @NotNull
    private String telefono;

    @Column(name = "contrasena", nullable = false)
    @NotNull
    @Size(min = 8, max = 128)
    private String contrasena;

    @Column(name = "descripcion")
    @NotNull
    private String descripcion;

    @NotNull
    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "intereses")
    private String intereses;

    @Lob
    @Column(name = "fotos")
    private String fotos;

}
