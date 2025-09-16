package com.tonolandia.demo.repository.usuario;

import com.tonolandia.demo.entity.Usuario;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UsuarioListPagingRepo extends ListPagingAndSortingRepository<Usuario, Long> {
}
