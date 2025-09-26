package com.tonolandia.demo.controllers;

import com.tonolandia.demo.dto.EventoDto;
import com.tonolandia.demo.dto.UsuarioCreateDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuarios", description = "Endpoints de usuarios con validación Jakarta")
@RestController
@RequestMapping("/api")
@Validated
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear usuario",
            description = "Crea un usuario aplicando las reglas de Jakarta Validation sobre el DTO",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Creado",
                            content = @Content(schema = @Schema(implementation = Usuario.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos",
                            content = @Content)
            }
    )
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioCreateDto dto) {
        Usuario creado = service.crear(dto);
        return ResponseEntity.status(201).body(creado);
    }

    @Operation(summary = "¿Existe por email?", description = "Demuestra validación de parámetros con @Validated")
    @GetMapping("/usuarios/existe")
    public ResponseEntity<Boolean> existe(@RequestParam @NotBlank @Email String email) {
        return ResponseEntity.ok(service.existePorEmail(email));
    }

    @Operation(summary = "Validar rango de fechas", description = "Ejemplo de validación de clase (fin > inicio)")
    @PostMapping("/eventos/validar")
    public ResponseEntity<String> validarEvento(@Valid @RequestBody EventoDto dto) {
        return ResponseEntity.ok("Rango válido");
    }
}