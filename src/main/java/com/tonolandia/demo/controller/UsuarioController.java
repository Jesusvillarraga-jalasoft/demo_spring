package com.tonolandia.demo.controller;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones CRUD con Soft Delete y Restore")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en el sistema")
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @Operation(summary = "Listar activos", description = "Lista solo los usuarios no eliminados (deleted = false)")
    @GetMapping
    public List<Usuario> listActivos() {
        return usuarioService.listActivos();
    }

    @Operation(summary = "Listar todos", description = "Lista todos los usuarios, incluidos eliminados")
    @GetMapping("/all")
    public List<Usuario> listTodos() {
        return usuarioService.listTodos();
    }

    @Operation(summary = "Listar eliminados", description = "Lista únicamente los usuarios que han sido eliminados (soft delete)")
    @GetMapping("/deleted")
    public List<Usuario> listEliminados() {
        return usuarioService.listEliminados();
    }

    @Operation(summary = "Eliminar usuario", description = "Soft delete de un usuario por ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @Operation(summary = "Restaurar usuario", description = "Restaura un usuario eliminado previamente")
    @PostMapping("/restore/{id}")
    public void restore(@PathVariable Long id) {
        usuarioService.restore(id);
    }

    @Operation(summary = "Listar por rol", description = "Usa un filtro dinámico para traer usuarios por rol (ADMIN, USER, etc.)")
    @GetMapping("/role/{role}")
    public List<Usuario> listByRole(@PathVariable String role) {
        return usuarioService.listByRole(role);
    }
}
