package com.tonolandia.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "perfiles")
@Data
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_seq")
    @SequenceGenerator(name = "perfil_seq", sequenceName = "perfil_sequence", allocationSize = 1)
    private Long id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;
}
