package com.unir.Usuarios.data;

import com.unir.Usuarios.model.db.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsuariosRepository {

    private final UsuariosJpaRepository repository;

    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Usuario getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario getByCorreo(String correo) throws Exception {
        return repository.findByCorreo(correo)
                .orElseThrow(() -> new Exception("Correo not found"));
    }

    public Usuario getByTelefono(String telefono) throws Exception {
        return repository.findByTelefono(telefono)
                .orElseThrow(() -> new Exception("Telefono not found"));
    }

    public List<Usuario> getByCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(Usuario usuario) {
        repository.delete(usuario);
    }
}
