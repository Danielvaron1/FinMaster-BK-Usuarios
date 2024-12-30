package com.unir.Usuarios.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "notificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    @JsonIgnore
    private Usuario usuarioId;

    @Column(name = "tipoId", nullable = false)
    private Long tipoId;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "fecha", nullable = false)
    private String fecha;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    public Notificacion(Usuario usuarioId, Long tipoId, String tipo) {
        this.usuarioId = usuarioId;
        this.tipoId = tipoId;
        this.tipo = tipo;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.fecha = now.format(formatter);
        this.leido = false;
    }
}
