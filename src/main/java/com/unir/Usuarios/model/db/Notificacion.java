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

    @Column(name = "usuarioId", nullable = false)
    @NotNull
    private Long usuarioId;

    @Column(name = "tipoId", nullable = false)
    @NotNull
    private Long tipoId;

    @Column(name = "tipo", nullable = false)
    @NotNull
    private String tipo;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private String fecha;

    public Notificacion(Long usuarioId, Long tipoId, String tipo) {
        this.usuarioId = usuarioId;
        this.tipoId = tipoId;
        this.tipo = tipo;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        System.out.println(formattedDate);
        this.fecha = formattedDate;
    }
}
