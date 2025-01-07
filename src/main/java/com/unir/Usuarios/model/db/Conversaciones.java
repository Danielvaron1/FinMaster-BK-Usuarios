package com.unir.Usuarios.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "conversaciones", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario1_id", "usuario2_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Conversaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id")
    private Usuario usuario2;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha", nullable = false)
    private String fecha;

    @Column(name = "ultimo_mensaje", nullable = false)
    private String ultimoMensaje;

    public void updateConversacion(String ultimoMensaje) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.fecha = now.format(formatter);
        this.setUltimoMensaje(ultimoMensaje);
    }

    public Conversaciones(Usuario usuario1, Usuario usuario2, String nombre) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.nombre = nombre;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.fecha = now.format(formatter);
        this.ultimoMensaje= "Chat nuevo";
    }

}
