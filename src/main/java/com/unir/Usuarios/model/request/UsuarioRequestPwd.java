package com.unir.Usuarios.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestPwd {

    @NotEmpty
    @NotNull
    private String contrasena;

    @NotEmpty
    @NotNull
    private String id;
}