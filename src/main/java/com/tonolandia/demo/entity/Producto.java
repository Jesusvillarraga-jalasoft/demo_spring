package com.tonolandia.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class Producto {
    @EmbeddedId
    private ProductoId id;

    private String nombre;
    private Double precio;
}
