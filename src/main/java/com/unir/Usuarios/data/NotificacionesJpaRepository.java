package com.unir.Usuarios.data;

import com.unir.Usuarios.model.db.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NotificacionesJpaRepository extends JpaRepository<Notificacion, Long>, JpaSpecificationExecutor<Notificacion> {
    List<Notificacion> findByUsuarioIdOrderByFecha(Long usuarioId);
}