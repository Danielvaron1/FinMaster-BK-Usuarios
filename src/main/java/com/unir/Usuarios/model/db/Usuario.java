package com.unir.Usuarios.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", unique = true, nullable = false)
    @Email
    private String correo;

    @Column(name = "fechaNacimiento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Column(name = "telefono", unique = true, nullable = false)
    private String telefono;

    @JsonIgnore
    @Column(name = "contrasena", nullable = false)
    @Size(min = 8, max = 128)
    private String contrasena;

    @Column(name = "descripcion",length = 1000)
    private String descripcion;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "intereses")
    private String intereses;

    @Lob
    @Column(name = "fotos")
    private String fotos;

    @OneToMany(
            mappedBy = "usuario1",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    @Transient
    private transient Set<Amigo> amigos;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    public String getPassword() {
        return contrasena;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return correo;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
