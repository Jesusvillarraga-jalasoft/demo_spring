package com.tonolandia.demo.controller.rol;

import com.tonolandia.demo.dto.RolCreateDto;
import com.tonolandia.demo.entity.Rol;
import com.tonolandia.demo.service.rol.RolService;
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
@RequestMapping("/roles")
@Tag(name = "Roles", description = "Operaciones CRUD para la entidad Rol (relación N:M con Usuario)")
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los roles")
    @GetMapping
    public List<Rol> listar() {
        return service.listarRoles();
    }

    @Operation(summary = "Buscar rol por ID")
    @GetMapping("/{id}")
    public Rol buscarPorId(
            @Parameter(description = "ID del rol", example = "1")
            @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(
            summary = "Crear un nuevo rol",
            description = "Crea un rol y lo asocia a usuarios existentes si se proporcionan IDs.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RolCreateDto.class),
                            examples = @ExampleObject(value = """
                    {
                      "nombre": "ADMIN",
                      "usuarioIds": [1, 2]
                    }
                """)
                    )
            )
    )
    @PostMapping
    public Rol crear(@RequestBody RolCreateDto dto) {
        return service.crearRol(dto);
    }

    @Operation(summary = "Actualizar un rol existente")
    @PutMapping("/{id}")
    public Rol actualizar(
            @Parameter(description = "ID del rol", example = "1") @PathVariable Long id,
            @RequestBody RolCreateDto dto) {
        return service.actualizarRol(id, dto);
    }

    @Operation(summary = "Eliminar un rol por ID")
    @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente")
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del rol", example = "1") @PathVariable Long id) {
        service.eliminarRol(id);
    }
}
