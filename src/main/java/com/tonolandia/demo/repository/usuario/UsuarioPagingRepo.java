package com.tonolandia.demo.repository.usuario;

import com.tonolandia.demo.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioPagingRepo extends PagingAndSortingRepository<Usuario, Long> {
}
