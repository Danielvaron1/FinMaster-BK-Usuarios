package com.unir.Usuarios.service;

import com.unir.Usuarios.data.ConversacionesJpaRepository;
import com.unir.Usuarios.model.db.Conversaciones;
import com.unir.Usuarios.model.db.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ConversacionesService {
    @Autowired
    UsuariosService usuariosService;

    @Autowired
    private ConversacionesJpaRepository repository;

    public Conversaciones createConversacion(String usuario1, String usuario2, String nombre) throws Exception {
        System.out.println("Usuarios: "+ usuario1 + " :"+usuario2);
        Usuario usuarioGet1 = usuariosService.getUsuario(usuario1,"","");
        Usuario usuarioGet2 = usuariosService.getUsuario(usuario2,"","");
        System.out.println("Usuario2"+ usuarioGet1.getCorreo()+ "GET: " + usuarioGet2.getCorreo());
        if(usuarioGet1 == null || usuarioGet2 == null){
            throw new Exception("No se encontro el usuario.");
        }
        Conversaciones notificacion = new Conversaciones(usuarioGet1, usuarioGet2, nombre);
        Conversaciones notificacion2 = new Conversaciones(usuarioGet2, usuarioGet1, nombre);
        repository.save(notificacion2);
        return repository.save(notificacion);
    }

    public List<Conversaciones> getConversaciones(String usuario1) throws Exception {
        Usuario usuario = usuariosService.getUsuario(usuario1,"","");
        if(usuario == null){
            throw new Exception("No se encontro el usuario.");
        }
        return repository.findByUsuario1OrderByFechaDesc(usuario);
    }

    public void updateConversacion(Long conversacionId, String ultimoMensaje ) throws Exception {
        Optional<Conversaciones> conversacion = repository.findById(conversacionId);
        if(conversacion.isPresent()){
            Conversaciones conversacionGet = conversacion.get();
            Usuario usuarioGet1 = conversacionGet.getUsuario1();
            Usuario usuarioGet2 = conversacionGet.getUsuario2();
            Conversaciones conversacion2 = repository.findByUsuario1AndUsuario2(usuarioGet2,usuarioGet1);
            conversacionGet.updateConversacion(ultimoMensaje);
            conversacion2.updateConversacion(ultimoMensaje);
            repository.save(conversacion2);
            repository.save(conversacionGet);
        } else {
            throw new Exception("No se encontro la conversacion.");
        }
    }

    public Conversaciones getConversacion(String usuario1, String usuario2) throws Exception {
        Usuario usuarioGet1 = usuariosService.getUsuario(usuario1,"","");
        Usuario usuarioGet2 = usuariosService.getUsuario(usuario2,"","");
        Conversaciones conversacion = repository.findByUsuario1AndUsuario2(usuarioGet2,usuarioGet1);
        if(conversacion == null){
            throw new Exception("No se encontro la conversacion.");
        }else{
            return conversacion;
        }
    }

    public Conversaciones getConversacion(String id) throws Exception {
        Conversaciones conversacion = repository.findById(Long.valueOf(id)).orElse(null);
        if(conversacion == null){
            throw new Exception("No se encontro la conversacion.");
        }else{
            return conversacion;
        }
    }
}
