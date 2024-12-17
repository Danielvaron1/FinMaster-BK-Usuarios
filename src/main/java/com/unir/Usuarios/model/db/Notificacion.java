package com.unir.Usuarios.model.db;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
    @NotNull
    private Usuario usuarioId;

    @Column(name = "tipoId", nullable = false)
    @NotNull
    private Long tipoId;

    @Column(name = "tipo", nullable = false)
    @NotNull
    private String tipo;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private String fecha;

    @Column(name = "leido", nullable = false)
    @NotNull
    private Boolean leido;

    public Notificacion(Usuario usuarioId, Long tipoId, String tipo) {
        this.usuarioId = usuarioId;
        this.tipoId = tipoId;
        this.tipo = tipo;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        System.out.println(formattedDate);
        this.fecha = formattedDate;
        this.leido = false;
    }
}
