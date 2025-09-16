package com.tonolandia.demo.controller.usuario;

import com.tonolandia.demo.dto.UsuarioCreateDto;
import com.tonolandia.demo.entity.Usuario;
import com.tonolandia.demo.entity.UsuarioConPerfilProjection;
import com.tonolandia.demo.record.UsuarioConPerfilDto;
import com.tonolandia.demo.service.usuario.UsuarioJpaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa")
@Tag(name = "Usuarios JPA", description = "Operaciones de ejemplo con JPA Repository: Query Methods, @Query, Named Queries, EntityGraph y Projections")
public class UsuarioJpaController {

    private final UsuarioJpaService service;

    public UsuarioJpaController(UsuarioJpaService service) {
        this.service = service;
    }

    // 🔹 Query Methods
    @Operation(
            summary = "Buscar usuarios por nombre (ignora mayúsculas/minúsculas)",
            description = "Devuelve una lista de usuarios cuyo nombre contenga el valor proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios encontrados",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class),
                                    examples = @ExampleObject(value = """
                        [
                          { "id": 1, "nombre": "Ana", "email": "ana@mail.com", "activo": true },
                          { "id": 2, "nombre": "Andrés", "email": "andres@mail.com", "activo": false }
                        ]
                        """)))
            }
    )
    @GetMapping("/nombre")
    public List<Usuario> buscarPorNombre(
            @Parameter(description = "Parte del nombre a buscar", example = "ana")
            @RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @Operation(
            summary = "Buscar usuario por email",
            description = "Devuelve un usuario exacto según email."
    )
    @GetMapping("/email")
    public Optional<Usuario> buscarPorEmail(
            @Parameter(description = "Email exacto del usuario", example = "ana@mail.com")
            @RequestParam String email) {
        return service.buscarPorEmail(email);
    }

    // 🔹 @Query con JQL
    @Operation(summary = "Listar usuarios activos (JQL)")
    @GetMapping("/activos")
    public List<Usuario> activos() { return service.buscarActivos(); }

    @Operation(summary = "Buscar usuarios cuyo nombre coincida con LIKE (JQL)")
    @GetMapping("/nombre-like")
    public List<Usuario> buscarPorNombreLike(
            @Parameter(description = "Fragmento del nombre", example = "an")
            @RequestParam String nombre) {
        return service.buscarPorNombreLike(nombre);
    }

    // 🔹 @Query con SQL nativo
    @Operation(summary = "Buscar usuario por email (SQL nativo)")
    @GetMapping("/email-nativo")
    public Usuario buscarPorEmailNativo(
            @Parameter(description = "Email exacto", example = "ana@mail.com")
            @RequestParam String email) {
        return service.buscarPorEmailNativo(email);
    }

    @Operation(summary = "Listar usuarios activos (SQL nativo)")
    @GetMapping("/activos-nativo")
    public List<Usuario> buscarActivosNativo() {
        return service.buscarActivosNativo();
    }

    // 🔹 Named Queries
    @Operation(summary = "Buscar usuarios por dominio de email (NamedQuery)")
    @GetMapping("/dominio")
    public List<Usuario> porDominio(
            @Parameter(description = "Dominio del correo", example = "@gmail.com")
            @RequestParam String dominio) {
        return service.buscarPorDominio(dominio);
    }

    @Operation(summary = "Listar usuarios activos (NamedQuery)")
    @GetMapping("/activos-named")
    public List<Usuario> activosNamed() { return service.buscarActivosNamed(); }

    // 🔹 EntityGraph
    @Operation(summary = "Buscar usuario con sus órdenes (forzando fetch LAZY → EAGER)")
    @GetMapping("/{id}/ordenes")
    public Usuario buscarUsuarioConOrdenes(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        return service.buscarUsuarioConOrdenes(id);
    }

    // 🔹 Projection (Interface)
    @Operation(summary = "Buscar usuario con perfil (solo ID del perfil, proyección interface)")
    @GetMapping("/{id}/perfil-solo-id")
    public UsuarioConPerfilProjection buscarUsuarioConPerfilSoloId(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        return service.buscarUsuarioConPerfilSoloId(id);
    }

    // 🔹 DTO Projection
    @Operation(summary = "Buscar usuario con perfil (DTO projection)")
    @GetMapping("/{id}/perfil-dto")
    public UsuarioConPerfilDto buscarUsuarioConPerfilDto(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        return service.buscarUsuarioConPerfilDto(id);
    }

    // 🔹 Crear usuarios con y sin transacción
    @Operation(
            summary = "Crear usuarios con transacción",
            description = "Demuestra rollback: si ocurre error, no se guarda ningún usuario.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultado de la operación",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Éxito", value = "\"Usuarios creados correctamente (transacción)\""),
                                            @ExampleObject(name = "Error", value = "\"Error: se hizo rollback, no se guardó nada.\"")
                                    }
                            )
                    )
            }
    )
    @PostMapping("/crear-con-transaccion")
    public String crearConTransaccion(@RequestBody UsuarioCreateDto dto) {
        try {
            service.crearUsuariosConTransaccion();
            return "Usuarios creados correctamente (transacción)";
        } catch (Exception e) {
            return "Error: se hizo rollback, no se guardó nada.";
        }
    }

    @Operation(
            summary = "Crear usuarios sin transacción",
            description = "Demuestra persistencia parcial: algunos usuarios quedan guardados aunque ocurra error."
    )
    @PostMapping("/crear-sin-transaccion")
    public String crearSinTransaccion(@RequestBody UsuarioCreateDto dto) {
        try {
            service.crearUsuariosSinTransaccion();
            return "Usuarios creados parcialmente (sin transacción).";
        } catch (Exception e) {
            return "Error: algunos usuarios pueden haberse guardado.";
        }
    }

    // 🔹 Demo Flush y Batch
    @Operation(
            summary = "Demostración de flush() y batch",
            description = "Guarda varios usuarios con diferentes estrategias de persistencia inmediata y elimina en batch."
    )
    @PostMapping("/demo-flush-batch")
    public String demoFlushBatch() {
        service.demoFlushAndBatch();
        return "Demo de flush y batch ejecutada";
    }
}
