package com.unir.Usuarios.model.db;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "amigos", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario1", "usuario2"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario1", nullable = false)
    @NotNull
    private String usuario1;

    @Column(name = "usuario2", nullable = false)
    @NotNull
    private String usuario2;

    @Column(name = "estado", nullable = false)
    @NotNull
    private String estado;

    public Amigo(String usuario1, String usuario2, String estado) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.estado = estado;
    }

    public void updateAmigo(String usuario1, String usuario2, String estado){
        this.setUsuario1(usuario1);
        this.setUsuario2(usuario2);
        this.setEstado(estado);
    }
}
