package com.tonolandia.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue("ADMIN_MINOR")
public class UsuarioAdminMinor extends UsuarioAdmin {
}
