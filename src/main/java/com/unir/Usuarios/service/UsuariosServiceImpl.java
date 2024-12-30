package com.unir.Usuarios.service;

import com.unir.Usuarios.data.UsuariosRepository;
import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.request.LoginRequest;
import com.unir.Usuarios.model.request.UsuarioRequest;
import com.unir.Usuarios.model.request.UsuarioRequestPwd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@Slf4j
public class UsuariosServiceImpl implements UsuariosService{

    private final UsuariosRepository repository;

    private final AuthenticationManager authenticationManager;


    public UsuariosServiceImpl(
            UsuariosRepository userRepository,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.repository = userRepository;
    }

    public Usuario authenticate(LoginRequest input) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getCorreo(),
                        input.getContrasena()
                )
        );

        return repository.getByCorreo(input.getCorreo());
    }

    @Override
    public List<Usuario> getUsuarios(String ciudad) {
        List<Usuario> usuarios;
        if (StringUtils.hasLength(ciudad)){
            usuarios = repository.getByCiudad(ciudad);
        } else {
            usuarios = repository.getUsuarios();
        }
        return usuarios.isEmpty() ? null : usuarios;
    }

    @Override
    public Usuario getUsuario(String usuarioId, String correo, String telefono) throws Exception {
        Usuario newUsuario;
        if (StringUtils.hasLength(usuarioId)) {
            newUsuario = repository.getById(Long.valueOf(usuarioId));
        } else if (StringUtils.hasLength(correo)) {
            newUsuario = repository.getByCorreo(correo);
        } else if (StringUtils.hasLength(telefono)) {
            newUsuario = repository.getByTelefono(telefono);
        } else {
            throw new Exception("No se encontró un usuario con los datos proporcionados");
        }
        if(newUsuario==null){
            throw new Exception("No se encontró un usuario con los datos proporcionados");
        } else{
            return newUsuario;
        }
    }

    @Override
    public Usuario createUsuario(UsuarioRequest request) throws Exception {
        request.setContrasena(new BCryptPasswordEncoder().encode(request.getContrasena()));
        if (!request.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("No es correcto el correo");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(request, usuario);
        return repository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(String usuarioId, UsuarioRequest body) throws Exception {
        Usuario usuario = repository.getById(Long.valueOf(usuarioId));
        if (usuario != null) {
            String userPwd = usuario.getContrasena();
            BeanUtils.copyProperties(body, usuario);
            usuario.setContrasena(userPwd);
            repository.save(usuario);
            return usuario;
        } else {
            throw new Exception("No se encontró un usuario el id a actualizar");
        }
    }

    @Override
    public void removeUsuario(String usuarioId) throws Exception {
        Usuario usuario = repository.getById(Long.valueOf(usuarioId));
        if (usuario != null){
            repository.delete(usuario);
        } else {
            throw new Exception("No se encontró un usuario el id a eliminar");
        }
    }

    @Override
    public void updatePwdUsuario(UsuarioRequestPwd body) throws Exception {
        Usuario usuario = repository.getById(Long.valueOf(body.getId()));
        body.setContrasena(new BCryptPasswordEncoder().encode(body.getContrasena()));
        if (usuario != null){
            usuario.setContrasena(body.getContrasena());
            repository.save(usuario);
        } else {
            throw new Exception("No se encontró un usuario el id a actualizar");
        }
    }
}
