package com.unir.Usuarios.controller;

import com.unir.Usuarios.model.db.Usuario;
import com.unir.Usuarios.model.request.LoginRequest;
import com.unir.Usuarios.model.request.LoginResponse;
import com.unir.Usuarios.model.request.UsuarioRequest;
import com.unir.Usuarios.service.UsuariosService;
import com.unir.Usuarios.token.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/Usuarios")
public class UsuariosController {

    private final JwtService jwtService;

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
    @PostMapping("/auth/singup")
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioRequest request) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createUsuario(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try{
            Usuario authenticatedUser = service.authenticate(request);

            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse(jwtToken,jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
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
