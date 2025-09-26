package com.tonolandia.demo.controller;


import com.tonolandia.demo.dto.UsuarioCreateDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.exception.error.ErrorResponse;
import com.tonolandia.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Crea un nuevo usuario", description = "Valida el payload con Bean Validation e inserta en H2")
    @ApiResponse(responseCode = "201", description = "Creado", content = @Content(schema = @Schema(implementation = Usuario.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\n  \"timestamp\":\"2025-09-26T11:32:45.123-05:00\",\n  \"status\":400,\n  \"error\":\"Bad Request\",\n  \"message\":\"El email es inválido\",\n  \"errors\":{\"email\":\"Debe ser un correo válido\"},\n  \"path\":\"/usuarios\"\n}")))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@Valid @RequestBody UsuarioCreateDto dto) {
        return service.crear(dto);
    }

    @Operation(summary = "Obtiene un usuario por id", description = "Lanza 404 si no existe")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Usuario.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@Parameter(description = "Identificador del usuario") @PathVariable @Min(value = 1, message = "{usuario.edad.min}") Long id) {
        return service.obtenerPorId(id, null);
    }
}
