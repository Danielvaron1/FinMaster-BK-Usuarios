package com.unir.Usuarios.service;

import com.unir.Usuarios.data.AmigosJpaRepository;
import com.unir.Usuarios.model.db.Amigo;
import com.unir.Usuarios.model.db.Usuario;
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
    private UsuariosService usuariosService;

    @Autowired
    private AmigosJpaRepository repository;

    public Amigo createAmigo(String usuario1, String usuario2) throws Exception {
        Usuario user1 = usuariosService.getUsuario("",usuario1,"");
        Usuario user2 = usuariosService.getUsuario("",usuario2,"");
        Amigo amigo = new Amigo(user1, user2, "pendiente");
        return repository.save(amigo);
    }

    public void acceptAmigo(String usuario1, String usuario2) throws Exception {
        Amigo amigo = getAmigo(usuario1, usuario2);
        if(amigo!=null){
            amigo.setEstado("aceptado");
            repository.save(amigo);
        } else {
            throw new Exception("No se encontro peticion de amigo.");
        }
        Amigo amigo2 = getAmigo(usuario2, usuario1);
        if (amigo2==null){
            amigo2 = new Amigo(amigo.getUsuario2(), amigo.getUsuario1(), "aceptado");
            repository.save(amigo2);
        } else {
            amigo2.setEstado("aceptado");
            repository.save(amigo2);
        }
    }

    public List<Amigo> getAmigos(String usuario) throws Exception {
        Usuario user = usuariosService.getUsuario("",usuario,"");
        Set<Amigo> list = new HashSet<>();
        repository.findByUsuario1(user).forEach(amigo -> list.add((Amigo) amigo));
        return new ArrayList<>(list);
    }

    public List<Amigo> getAmigos(String usuario, String estado) throws Exception {
        Usuario user = usuariosService.getUsuario("",usuario,"");
        List<Amigo> list = new ArrayList<>();
        repository.findByUsuario1AndEstado(user, estado).forEach(amigo -> list.add((Amigo) amigo));
        return list;
    }

    public Amigo getAmigo(String usuario1, String usuario2) throws Exception {
        Usuario user1 = usuariosService.getUsuario("",usuario1,"");
        Usuario user2 = usuariosService.getUsuario("",usuario2,"");
        return repository.findByUsuario1AndUsuario2(user1, user2);
    }

    public void deleteAmigo(String usuario1, String usuario2) throws Exception {
        Amigo amigo = getAmigo(usuario1, usuario2);
        Amigo amigo2 = getAmigo(usuario2, usuario1);
        if(amigo==null && amigo2==null){
            throw new Exception("No se encontraron amigos entre los usuarios");
        }
        if(amigo!=null){
            repository.delete(amigo);
        }
        if(amigo2!=null){
            repository.delete(amigo2);
        }
    }
}