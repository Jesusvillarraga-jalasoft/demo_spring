package com.tonolandia.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ProductoId implements Serializable {
    private String codigo;
    private String lote;
}