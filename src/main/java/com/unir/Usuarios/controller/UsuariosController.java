package com.unir.Usuarios.controller;

import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.db.UsuarioDto;
import com.unir.Usuarios.model.request.CreateUsuarioRequest;
import com.unir.Usuarios.service.UsuariosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsuariosController {

    private final UsuariosService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(required= false) String ciudad){
        List<Usuario> usuarios = service.getUsuarios(ciudad);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> getUsuario(@RequestParam(required= false) String usuarioId,
                                              @RequestParam(required= false) String correo,
                                              @RequestParam(required= false) String telefono){
        try{
            return ResponseEntity.ok(service.getUsuario(usuarioId, correo, telefono));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody CreateUsuarioRequest request) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createUsuario(request));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String usuarioId, @RequestBody UsuarioDto body) {
        try{
            return ResponseEntity.ok(service.updateUsuario(usuarioId, body));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String usuarioId) {
        try{
            service.removeUsuario(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/hello-secure")
    public String helloSec(){
        return "Hello world sec";
    }
}
