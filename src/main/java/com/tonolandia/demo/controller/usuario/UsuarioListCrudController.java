package com.tonolandia.demo.controller.usuario;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.service.usuario.UsuarioListCrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list-crud")
public class UsuarioListCrudController {
    private final UsuarioListCrudService service;

    public UsuarioListCrudController(UsuarioListCrudService service) { this.service = service; }

    @GetMapping
    public List<Usuario> listar() { return service.listar(); }
}
