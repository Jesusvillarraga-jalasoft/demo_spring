package com.tonolandia.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE usuario SET deleted = true, deleted_at = now(), deleted_by = 'system' WHERE id = ?")
@SQLRestriction("deleted = false")
@FilterDef(name = "roleFilter", parameters = @ParamDef(name = "roleParam", type = String.class))
@Filter(name = "roleFilter", condition = "role = :roleParam")
public class Usuario extends BaseEntity {

    private String nombre;
    private String email;
    private String role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLDelete(sql = "UPDATE direccion SET deleted = true, deleted_at = now(), deleted_by = 'system' WHERE id = ?")
    @SQLRestriction("deleted = false")
    private List<Direccion> direcciones = new ArrayList<>();
}