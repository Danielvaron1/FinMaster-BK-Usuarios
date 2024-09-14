package com.unir.Usuarios.service;

import com.unir.Usuarios.data.UsuariosRepository;
import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.db.UsuarioDto;
import com.unir.Usuarios.model.request.CreateUsuarioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@Slf4j
public class UsuariosServiceImpl implements UsuariosService{

    @Autowired
    private UsuariosRepository repository;

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
        if (StringUtils.hasLength(usuarioId)) {
            return repository.getById(Long.valueOf(usuarioId));
        } else if (StringUtils.hasLength(correo)) {
            return repository.getByCorreo(correo);
        } else if (StringUtils.hasLength(telefono)) {
            return repository.getByTelefono(telefono);
        } else {
            throw new Exception("No se encontró un usuario con los datos proporcionados");
        }
    }

    @Override
    public Usuario createUsuario(CreateUsuarioRequest request) throws Exception {
        //request.setContrasena(hashContrasena(request.getContrasena()));
        if (!request.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("No es correcto el correo");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(request, usuario);
        return repository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(String usuarioId, UsuarioDto body) throws Exception {
        if (!body.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("No es correcto el correo");
        }
        Usuario usuario = repository.getById(Long.valueOf(usuarioId));
        if (usuario != null) {
            BeanUtils.copyProperties(body, usuario);
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

    /**
    private String hashContrasena(String password){
        String salt = BCrypt.gensalt();
        System.out.println(BCrypt.hashpw(password, salt));
        return BCrypt.hashpw(password, salt);
    }

    private boolean verifyContrasena(String password, String hashedPassword){
        String inputPassword = "mysecretpassword";
        //String storedHash =  //obtener el hash almacenado en la base de datos

        //if (BCrypt.checkpw(inputPassword, storedHash)) {
            // La contraseña es válida
        //} else {
            // La contraseña es inválida
        //}
        return true;
    }
     **/
}
