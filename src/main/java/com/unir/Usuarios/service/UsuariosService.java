package com.unir.Usuarios.service;

import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.db.UsuarioDto;
import com.unir.Usuarios.model.request.CreateUsuarioRequest;

import java.util.List;

public interface UsuariosService {

    List<Usuario> getUsuarios(String ciudad);

    Usuario getUsuario(String usuarioId, String correo, String telefono) throws Exception;

    Usuario createUsuario(CreateUsuarioRequest request) throws Exception;

    Usuario updateUsuario(String usuarioId, UsuarioDto body) throws Exception;

    void removeUsuario(String usuarioId) throws Exception;
}
