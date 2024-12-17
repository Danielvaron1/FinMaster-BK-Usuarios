package com.unir.Usuarios.controller;

import com.unir.Usuarios.model.db.Amigo;
import com.unir.Usuarios.service.AmigosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("denyAll()")
@RequestMapping("/v1/Amigos")
public class AmigosController {

    private final AmigosService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Amigo> createAmigo(@RequestBody Map<String, String> request) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createAmigo(request.get("usuario1"), request.get("usuario2")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/amigo")
    public ResponseEntity acceptAmigo(@RequestParam String usuario1,
                                      @RequestParam String usuario2){
        try{
            service.acceptAmigo(usuario1, usuario2);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Amigo>> getAmigos(@RequestParam String usuario,
                                                @RequestParam(required= false) String estado){
        try{
            if(estado == null){
                return ResponseEntity.ok(service.getAmigos(usuario));
            } else{
                return ResponseEntity.ok(service.getAmigos(usuario, estado));
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/amigo")
    public ResponseEntity<Amigo> getAmigo(@RequestParam String usuario1,
                                          @RequestParam String usuario2){
        try{
            return ResponseEntity.ok(service.getAmigo(usuario1, usuario2));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/amigo")
    public ResponseEntity deleteAmigo(@RequestParam String usuario1,
                                      @RequestParam String usuario2){
        try{
            service.deleteAmigo(usuario1, usuario2);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
