package com.unir.Usuarios.model.db;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "amigos", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario1_id", "usuario2_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    @NotNull
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id")
    @NotNull
    private Usuario usuario2;

    @Column(name = "estado", nullable = false)
    @NotNull
    private String estado;

    public Amigo(Usuario usuario1, Usuario usuario2, String estado) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.estado = estado;
    }

    public void updateAmigo(Usuario usuario1, Usuario usuario2, String estado){
        this.setUsuario1(usuario1);
        this.setUsuario2(usuario2);
        this.setEstado(estado);
    }
}
