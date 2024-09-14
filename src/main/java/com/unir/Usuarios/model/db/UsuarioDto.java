package com.unir.Usuarios.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsuarioDto {

    private String nombre;
    private String apellido;
    private String correo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String telefono;
    private String contrasena;
    private String descripcion;
    private String ciudad;
    private List<String> intereses;
    private String fotos;
}
