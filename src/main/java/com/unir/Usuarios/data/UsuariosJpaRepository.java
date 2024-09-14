package com.unir.Usuarios.data;

import com.unir.Usuarios.model.db.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface UsuariosJpaRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    List<Usuario> findByCorreo(String correo);

    List<Usuario> findByTelefono(String telefono);

    List<Usuario> findByCiudad(String ciudad);

}
