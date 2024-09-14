package com.unir.Usuarios.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @Column(name = "nombre")
    @NotNull
    private String nombre;

    @Column(name = "apellido")
    @NotNull
    private String apellido;

    @Column(name = "correo", unique = true)
    @NotNull
    @Email
    private String correo;

    @Column(name = "fechaNacimiento")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Column(name = "telefono", unique = true)
    @NotNull
    private String telefono;

    @Column(name = "contrasena")
    @NotNull
    @Size(min = 8, max = 128)
    private String contrasena;

    @Column(name = "descripcion")
    @NotNull
    private String descripcion;

    @NotNull
    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "intereses")
    private List<String> intereses;

    @Lob
    @Column(name = "fotos")
    private String fotos;

}
