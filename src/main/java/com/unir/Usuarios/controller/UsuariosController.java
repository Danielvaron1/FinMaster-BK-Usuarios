package com.unir.Usuarios.controller;

import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.request.UsuarioRequest;
import com.unir.Usuarios.service.UsuariosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("denyAll()")
@RequestMapping("/v1/Usuarios")
public class UsuariosController {

    private final UsuariosService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(required= false) String ciudad){
        List<Usuario> usuarios = service.getUsuarios(ciudad);
        return ResponseEntity.ok(usuarios);
    }

    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioRequest request) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createUsuario(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/usuario/{usuarioId}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String usuarioId, @RequestBody UsuarioRequest body) {
        try{
            return ResponseEntity.ok(service.updateUsuario(usuarioId, body));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String usuarioId) {
        try{
            service.removeUsuario(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
