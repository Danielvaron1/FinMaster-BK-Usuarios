package com.unir.Usuarios.service;

import com.unir.Usuarios.data.NotificacionesJpaRepository;
import com.unir.Usuarios.model.db.Notificacion;
import com.unir.Usuarios.model.db.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NotificacionesService {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    private NotificacionesJpaRepository repository;

    public Notificacion createNotificacion(String usuarioId, Long tipoId, String tipo, String nombre) throws Exception {
        Usuario usuario = usuariosService.getUsuario(usuarioId,"","");
        if(usuario == null){
            throw new Exception("No se encontro el usuario.");
        }
        Notificacion notificacion = new Notificacion(usuario, tipoId, tipo,nombre);
        return repository.save(notificacion);
    }

    public List<Notificacion> getNotificaciones(String usuarioId) throws Exception {
        Usuario usuario = usuariosService.getUsuario(usuarioId,"","");
        if(usuario == null){
            throw new Exception("No se encontro el usuario.");
        }
        return repository.findByUsuarioIdOrderByFechaDesc(usuario);
    }

    public List<Notificacion> readNotificaciones(String usuarioId) throws Exception {
        Usuario usuario = usuariosService.getUsuario(usuarioId,"","");
        if(usuario == null){
            throw new Exception("No se encontro el usuario.");
        }
        List<Notificacion> notificaciones =repository.findByUsuarioIdOrderByFechaDesc(usuario);
        for (Notificacion notificacion : notificaciones) {
            notificacion.setLeido(true);
            repository.save(notificacion);
        }
        return notificaciones;
    }

    public void deleteNotificacion(Long id) throws Exception {
        Optional<Notificacion> notificacion = repository.findById(id);
        if(notificacion.isPresent()){
            repository.delete(notificacion.get());
        } else {
            throw new Exception("No se encontro notificacion con ese id");
        }
    }
}
