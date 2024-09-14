package com.unir.Usuarios.service;

import com.unir.Usuarios.data.NotificacionesJpaRepository;
import com.unir.Usuarios.model.db.Notificacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NotificacionesService {

    @Autowired
    private NotificacionesJpaRepository repository;

    public Notificacion createNotificacion(Long usuarioId, Long tipoId, String tipo){
        Notificacion notificacion = new Notificacion(usuarioId, tipoId, tipo);
        return repository.save(notificacion);
    }

    public List<Notificacion> getNotificaciones(Long usuarioId){
        return repository.findByUsuarioIdOrderByFecha(usuarioId);
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
