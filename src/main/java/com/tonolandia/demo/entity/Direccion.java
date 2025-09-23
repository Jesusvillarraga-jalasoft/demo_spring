package com.tonolandia.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE direccion SET deleted = true, deleted_at = now(), deleted_by = 'system' WHERE id = ?")
@SQLRestriction("deleted = false")
public class Direccion extends BaseEntity {

    private String ciudad;
    private String calle;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
