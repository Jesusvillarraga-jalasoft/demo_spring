package com.tonolandia.demo.controller.orden;

import com.tonolandia.demo.dto.OrdenCreateDto;
import com.tonolandia.demo.entity.Orden;
import com.tonolandia.demo.service.orden.OrdenService;
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
@RequestMapping("/ordenes")
@Tag(name = "Órdenes", description = "Operaciones CRUD para la entidad Orden (relación N:1 con Usuario)")
public class OrdenController {

    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas las órdenes")
    @GetMapping
    public List<Orden> listar() {
        return service.listarOrdenes();
    }

    @Operation(summary = "Buscar orden por ID")
    @GetMapping("/{id}")
    public Orden buscarPorId(
            @Parameter(description = "ID de la orden", example = "1")
            @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Listar todas las órdenes de un usuario")
    @GetMapping("/usuario/{usuarioId}")
    public List<Orden> listarPorUsuario(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @Operation(
            summary = "Crear una nueva orden",
            description = "Crea una orden asociada a un usuario existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = OrdenCreateDto.class),
                            examples = @ExampleObject(value = """
                    { "descripcion": "Compra de teclado mecánico", "usuarioId": 1 }
                """)
                    )
            )
    )
    @PostMapping
    public Orden crear(@RequestBody OrdenCreateDto dto) {
        return service.crearOrden(dto);
    }

    @Operation(summary = "Actualizar una orden existente")
    @PutMapping("/{id}")
    public Orden actualizar(
            @Parameter(description = "ID de la orden", example = "1") @PathVariable Long id,
            @RequestBody OrdenCreateDto dto) {
        return service.actualizarOrden(id, dto);
    }

    @Operation(summary = "Eliminar una orden por ID")
    @ApiResponse(responseCode = "204", description = "Orden eliminada correctamente")
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la orden", example = "1") @PathVariable Long id) {
        service.eliminarOrden(id);
    }
}
