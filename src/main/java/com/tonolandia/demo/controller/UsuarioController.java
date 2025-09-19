package com.tonolandia.demo.controller;

import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.entity.UsuarioAdmin;
import com.tonolandia.demo.entity.UsuarioAdminMajor;
import com.tonolandia.demo.entity.UsuarioRegular;
import com.tonolandia.demo.repository.UsuarioAdminRepository;
import com.tonolandia.demo.repository.UsuarioQueriesRepository;
import com.tonolandia.demo.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones sobre la jerarquía Usuario (Regular y Admin)")
public class UsuarioController {

    private final UsuarioRepository usuarioRepo;
    private final UsuarioAdminRepository adminRepo;
    private final UsuarioQueriesRepository queriesRepo;

    public UsuarioController(UsuarioRepository usuarioRepo,
                             UsuarioAdminRepository adminRepo,
                             UsuarioQueriesRepository queriesRepo) {
        this.usuarioRepo = usuarioRepo;
        this.adminRepo = adminRepo;
        this.queriesRepo = queriesRepo;
    }

    @Operation(
            summary = "Listar todos los usuarios",
            description = "Consulta polimórfica: devuelve tanto usuarios regulares como admins"
    )
    @ApiResponse(responseCode = "200", description = "Lista de usuarios",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class)))
    @GetMapping
    public List<Usuario> all() {
        return usuarioRepo.findAll();
    }

    @Operation(
            summary = "Listar solo administradores",
            description = "Consulta al repositorio específico de UsuarioAdmin"
    )
    @GetMapping("/admins")
    public List<UsuarioAdmin> admins() {
        return adminRepo.findAll();
    }

    @Operation(
            summary = "Listar solo administradores con TYPE()",
            description = "Usa JPQL con type(u) = UsuarioAdmin"
    )
    @GetMapping("/admins/type")
    public List<Usuario> adminsWithType() {
        return queriesRepo.findAdminsWithType();
    }

    @GetMapping("/adminsMajor/type")
    public List<Usuario> adminsWithTypeMajor() {
        return queriesRepo.findAdminsWithTypeMajor();
    }


    @Operation(summary = "Crear usuario regular")
    @PostMapping("/regular")
    public UsuarioRegular createRegular(@RequestBody UsuarioRegular u) {
        return usuarioRepo.save(u);
    }

    @Operation(summary = "Crear usuario admin")
    @PostMapping("/admin")
    public UsuarioAdmin createAdmin(@RequestBody UsuarioAdmin u) {
        return usuarioRepo.save(u);
    }

    @Operation(summary = "Crear usuario admin")
    @PostMapping("/adminMajor")
    public UsuarioAdmin createAdminMajor(@RequestBody UsuarioAdminMajor u) {
        return usuarioRepo.save(u);
    }
}