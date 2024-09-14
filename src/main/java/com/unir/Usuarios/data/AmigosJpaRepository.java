package com.unir.Usuarios.data;

import com.unir.Usuarios.model.db.Amigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AmigosJpaRepository extends JpaRepository<Amigo, Long>, JpaSpecificationExecutor<Amigo> {
    Iterable<Object> findByUsuario1OrUsuario2(String usuario1, String usuario2);

    Iterable<Object> findByUsuario1AndUsuario2(String usuario1, String usuario2);

    Iterable<Object> findByUsuario1AndEstado(String usuario, String estado);

    Iterable<Object> findByUsuario2AndEstado(String usuario, String estado);
}
