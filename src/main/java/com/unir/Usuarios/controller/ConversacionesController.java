package com.unir.Usuarios.controller;

import com.unir.Usuarios.model.db.Conversaciones;
import com.unir.Usuarios.service.ConversacionesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("denyAll()")
@RequestMapping("/v1/Conversaciones")
public class ConversacionesController {

    private final ConversacionesService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{usuario1}")
    public ResponseEntity<List<Conversaciones>> getConversaciones(@PathVariable String usuario1) {
        try{
            return ResponseEntity.ok(service.getConversaciones(usuario1));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<Conversaciones> getConversacion(@RequestParam String usuario1,@RequestParam String usuario2) {
        try{
            return ResponseEntity.ok(service.getConversacion(usuario1,usuario2));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Conversaciones> createConversacion(@RequestParam String usuario1, @RequestParam String usuario2, @RequestParam String nombre) {
        try{
            return ResponseEntity.ok(service.createConversacion(usuario1,usuario2,nombre));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateConversacion(@PathVariable Long id,@RequestParam String ultimoMensaje) {
        try {
            service.updateConversacion(id,ultimoMensaje);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
