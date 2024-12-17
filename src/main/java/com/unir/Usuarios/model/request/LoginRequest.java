package com.unir.Usuarios.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotEmpty
    @NotNull
    @Email
    private String correo;

    @NotEmpty
    @NotNull
    private String contrasena;

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
