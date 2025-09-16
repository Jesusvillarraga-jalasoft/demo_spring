package com.tonolandia.demo.controller.usuario;

import com.tonolandia.demo.service.usuario.UsuarioListPagingService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list-paging")
public class UsuarioListPagingController {
    private final UsuarioListPagingService service;

    public UsuarioListPagingController(UsuarioListPagingService service) { this.service = service; }

    @GetMapping
    public Object listar(Pageable pageable) {
        return service.listar(pageable);
    }
}
