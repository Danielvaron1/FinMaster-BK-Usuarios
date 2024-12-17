package com.unir.Usuarios.data;

import com.unir.Usuarios.model.db.Amigo;
import com.unir.Usuarios.model.db.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AmigosJpaRepository extends JpaRepository<Amigo, Long>, JpaSpecificationExecutor<Amigo> {

    Amigo findByUsuario1AndUsuario2(Usuario usuario1, Usuario usuario2);

    Iterable<Object> findByUsuario1AndEstado(Usuario usuario, String estado);

    Iterable<Object> findByUsuario2AndEstado(Usuario usuario, String estado);

    Iterable<Object> findByUsuario1(Usuario user);
}
