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

    public Usuario getByCorreo(String correo) {
        return repository.findByCorreo(correo).getFirst();
    }

    public Usuario getByTelefono(String telefono) {
        return repository.findByTelefono(telefono).getFirst();
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
