package com.tonolandia.demo.api;

import com.tonolandia.demo.dto.AsignacionRequest;
import com.tonolandia.demo.dto.AsignacionResponse;
import com.tonolandia.demo.dto.CierreRequest;
import com.tonolandia.demo.dto.ContratoDto;
import com.tonolandia.demo.service.AltaMesaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionController {

    private final AltaMesaService service;
    public AsignacionController(AltaMesaService service) { this.service = service; }

    /**
     * Endpoint POST /api/asignaciones
     *
     * Crea una nueva asignación.
     *
     * @param req objeto {@link AsignacionRequest} con los datos de la asignación
     * @return objeto {@link AsignacionResponse} con el resultado de la operación
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AsignacionResponse asignar(@RequestBody AsignacionRequest req) {
        return service.asignar(req);
    }

    /**
     * Endpoint POST /api/asignaciones/cierre
     *
     * Cierra una o varias asignaciones.
     *
     * @param req objeto {@link CierreRequest} con la información necesaria para el cierre
     * @return lista de {@link ContratoDto} con los contratos cerrados
     */
    @PostMapping("/cierre")
    public List<ContratoDto> cerrar(@RequestBody CierreRequest req) {
        return service.cerrar(req);
    }
}