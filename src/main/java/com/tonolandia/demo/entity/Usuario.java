package com.tonolandia.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@NamedQueries({
        @NamedQuery(name = "Usuario.buscarPorDominioEmail",
                query = "SELECT u FROM Usuario u WHERE u.email LIKE %:dominio%"),
        @NamedQuery(name = "Usuario.buscarActivos",
                query = "SELECT u FROM Usuario u WHERE u.activo = true")
})
@Data
public class Usuario implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private boolean activo;

    // Perfil con EAGER
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Perfil perfil;

    // Órdenes con LAZY
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Orden> ordenes = new ArrayList<>();

    // Roles con LAZY
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    @Transient
    private boolean isNew = true;

    @PreRemove
    public void preRemove() {
        System.out.println(">>> Se va a remover Usuario: " + nombre);
    }

    @PostRemove
    public void postRemove() {
        System.out.println(">>> Se removio Usuario: " + nombre);
    }

    @Override
    public boolean isNew() { return isNew; }

    public void markAsNotNew() { this.isNew = false; }
}
