package com.unir.Usuarios.data;

import com.unir.Usuarios.model.db.Conversaciones;
import com.unir.Usuarios.model.db.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConversacionesJpaRepository extends JpaRepository<Conversaciones, Long>, JpaSpecificationExecutor<Conversaciones> {

    List<Conversaciones> findByUsuario1OrderByFechaDesc(Usuario usuario1);

    Conversaciones findByUsuario1AndUsuario2(Usuario usuario1, Usuario usuario2);


}
