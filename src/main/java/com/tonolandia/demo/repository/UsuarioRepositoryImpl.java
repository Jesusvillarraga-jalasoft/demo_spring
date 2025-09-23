package com.tonolandia.demo.repository;

import com.tonolandia.demo.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> findByRoleUsingFilter(String role) {
        Session session = entityManager.unwrap(Session.class);

        // Activar filtro dinámico
        session.enableFilter("roleFilter").setParameter("roleParam", role);

        // Hacer la query normal (Hibernate añade la condición automáticamente)
        List<Usuario> usuarios = session.createQuery("from Usuario", Usuario.class).getResultList();

        // Desactivar filtro para no afectar futuras queries
        session.disableFilter("roleFilter");

        return usuarios;
    }
}
