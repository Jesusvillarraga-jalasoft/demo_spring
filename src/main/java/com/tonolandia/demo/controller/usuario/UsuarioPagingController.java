package com.tonolandia.demo.controller.usuario;

import com.tonolandia.demo.service.usuario.UsuarioPagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paging")
public class UsuarioPagingController {
    private final UsuarioPagingService service;

    public UsuarioPagingController(UsuarioPagingService service) { this.service = service; }

    @GetMapping
    public Object listar(@RequestParam int page, @RequestParam int size) {
        return service.listar(page, size);
    }
}