package com.unir.Usuarios.service;

import com.unir.Usuarios.data.AmigosJpaRepository;
import com.unir.Usuarios.model.db.Amigo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AmigosService {

    @Autowired
    private AmigosJpaRepository repository;

    public Amigo createAmigo(String usuario1, String usuario2){
        Amigo amigo = new Amigo(usuario1, usuario2, "pendiente");
        return repository.save(amigo);
    }

    public void acceptAmigo(String usuario1, String usuario2) throws Exception {
        Amigo amigo = getAmigo(usuario1, usuario2);
        if(amigo!=null){
            amigo.setEstado("aceptado");
            repository.save(amigo);
        } else {
            throw new Exception("Se encontraron mas de una peticion de amigos");
        }
    }

    public List<Amigo> getAmigos(String usuario){
        Set<Amigo> list = new HashSet<>();
        repository.findByUsuario1OrUsuario2(usuario, usuario).forEach(amigo -> list.add((Amigo) amigo));
        return new ArrayList<>(list);
    }

    public List<Amigo> getAmigos(String usuario, String estado){
        List<Amigo> list = new ArrayList<>();
        repository.findByUsuario1AndEstado(usuario, estado).forEach(amigo -> list.add((Amigo) amigo));
        repository.findByUsuario2AndEstado(usuario, estado).forEach(amigo -> list.add((Amigo) amigo));
        return list;
    }

    public Amigo getAmigo(String usuario1, String usuario2) throws Exception {
        List<Amigo> list = new ArrayList<>();
        repository.findByUsuario1AndUsuario2(usuario1, usuario2).forEach(amigo -> list.add((Amigo) amigo));
        repository.findByUsuario1AndUsuario2(usuario2, usuario1).forEach(amigo -> list.add((Amigo) amigo));
        if(list.size()==1){
            return list.get(0);
        } else{
            throw new Exception("Se encontraron mas de una peticion de amigos");
        }
    }

    public void deleteAmigo(String usuario1, String usuario2) throws Exception {
        Amigo amigo = getAmigo(usuario1, usuario2);
        if(amigo!=null){
            repository.delete(amigo);
        } else {
            throw new Exception("No se encontraron amigos entre los usuarios");
        }
    }
}