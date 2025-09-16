package com.tonolandia.demo.controller.perfil;

import com.tonolandia.demo.dto.PerfilCreateDto;
import com.tonolandia.demo.entity.Perfil;
import com.tonolandia.demo.service.perfil.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
@Tag(name = "Perfiles", description = "Operaciones CRUD para la entidad Perfil (relación 1:1 con Usuario)")
public class PerfilController {

    private final PerfilService service;

    public PerfilController(PerfilService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los perfiles")
    @GetMapping
    public List<Perfil> listar() {
        return service.listarPerfiles();
    }

    @Operation(summary = "Buscar perfil por ID")
    @GetMapping("/{id}")
    public Perfil buscarPorId(
            @Parameter(description = "ID del perfil", example = "1")
            @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(
            summary = "Crear un nuevo perfil",
            description = "Crea un perfil y lo asocia a un usuario existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = PerfilCreateDto.class),
                            examples = @ExampleObject(value = """
                    { "bio": "Desarrollador backend con experiencia en Spring Boot", "usuarioId": 1 }
                """)
                    )
            )
    )
    @PostMapping
    public Perfil crear(@RequestBody PerfilCreateDto dto) {
        return service.crearPerfil(dto);
    }

    @Operation(summary = "Actualizar un perfil existente")
    @PutMapping("/{id}")
    public Perfil actualizar(
            @Parameter(description = "ID del perfil", example = "1") @PathVariable Long id,
            @RequestBody PerfilCreateDto dto) {
        return service.actualizarPerfil(id, dto);
    }

    @Operation(summary = "Eliminar un perfil por ID")
    @ApiResponse(responseCode = "204", description = "Perfil eliminado correctamente")
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del perfil", example = "1") @PathVariable Long id) {
        service.eliminarPerfil(id);
    }
}
