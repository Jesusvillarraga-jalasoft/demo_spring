package com.tonolandia.demo.repository.usuario;

import com.tonolandia.demo.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepo extends CrudRepository<Usuario, Long> {
}
