package com.unir.Usuarios.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioRequest {

    @NotEmpty
    @NotNull
    private String nombre;

    @NotEmpty
    @NotNull
    private String apellido;

    @NotEmpty
    @NotNull
    @Email
    private String correo;

    @NotEmpty
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotEmpty
    @NotNull
    private String telefono;

    @NotEmpty
    @NotNull
    private String contrasena;

    @NotEmpty
    @NotNull
    private String descripcion;

    @NotEmpty
    @NotNull
    private String ciudad;

    @NotEmpty
    @NotNull
    private List<String> intereses;

    @NotEmpty
    @NotNull
    private String fotos;
}