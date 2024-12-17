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

    public Notificacion createNotificacion(Long usuarioId, Long tipoId, String tipo) throws Exception {
        Usuario usuario = usuariosService.getUsuario(String.valueOf(usuarioId),"","");
        Notificacion notificacion = new Notificacion(usuario, tipoId, tipo);
        return repository.save(notificacion);
    }

    public List<Notificacion> getNotificaciones(Long usuarioId) throws Exception {
        Usuario usuario = usuariosService.getUsuario(String.valueOf(usuarioId),"","");
        return repository.findByUsuarioIdOrderByFecha(usuario);
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
