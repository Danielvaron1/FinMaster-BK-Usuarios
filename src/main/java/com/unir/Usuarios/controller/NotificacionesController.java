package com.unir.Usuarios.controller;

import com.unir.Usuarios.model.db.Notificacion;
import com.unir.Usuarios.service.NotificacionesService;
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
@RequestMapping("/v1/Notificaciones")
public class NotificacionesController {

    private final NotificacionesService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Notificacion>> getNotificaciones(@RequestParam String usuarioId) {
        try{
            return ResponseEntity.ok(service.getNotificaciones(usuarioId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<List<Notificacion>> readNotificaciones(@RequestParam String usuarioId) {
        try{
            return ResponseEntity.ok(service.readNotificaciones(usuarioId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestParam String usuarioId,@RequestParam Long tipoId,@RequestParam String tipo,@RequestParam String nombre) {
        try{
            return ResponseEntity.ok(service.createNotificacion(usuarioId,tipoId,tipo,nombre));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        try {
            service.deleteNotificacion(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
