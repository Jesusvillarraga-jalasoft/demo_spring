package com.tonolandia.demo.controller.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.service.usuario.UsuarioCrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crud")
public class UsuarioCrudController {
    private final UsuarioCrudService service;

    public UsuarioCrudController(UsuarioCrudService service) { this.service = service; }

    @GetMapping
    public Iterable<Usuario> listar() { return service.listar(); }
}
