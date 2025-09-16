package com.tonolandia.demo.entity;

import java.util.List;

public interface UsuarioConPerfilProjection {
    Long getId();
    String getNombre();
    String getEmail();
    Perfil getPerfil();

    List<RolIdOnly> getRoles();
    interface RolIdOnly {
        Long getId();
    }

    List<OrdenIdOnly> getOrdenes();
    interface OrdenIdOnly {
        Long getId();
    }
}
