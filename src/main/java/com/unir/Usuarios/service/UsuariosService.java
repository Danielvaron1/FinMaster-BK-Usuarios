package com.unir.Usuarios.service;

import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.request.LoginRequest;
import com.unir.Usuarios.model.request.UsuarioRequest;
import com.unir.Usuarios.model.request.UsuarioRequestPwd;

import java.util.List;

public interface UsuariosService {

    List<Usuario> getUsuarios(String ciudad);

    Usuario getUsuario(String usuarioId, String correo, String telefono) throws Exception;

    Usuario createUsuario(UsuarioRequest request) throws Exception;

    Usuario authenticate(LoginRequest request) throws Exception;

    Usuario updateUsuario(String usuarioId, UsuarioRequest body) throws Exception;

    void removeUsuario(String usuarioId) throws Exception;

    void updatePwdUsuario(UsuarioRequestPwd body) throws Exception;
}
