package com.tonolandia.demo.api;

import com.tonolandia.demo.dto.ReporteContratosDto;
import com.tonolandia.demo.service.AltaMesaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final AltaMesaService service;
    public ReporteController(AltaMesaService service) { this.service = service; }

    /**
     * Endpoint GET /api/reportes/contratos
     *
     * Obtiene el reporte de contratos.
     *
     * @return objeto {@link ReporteContratosDto} con la información del reporte
     */
    @GetMapping("/contratos")
    public ReporteContratosDto reporteContratos() {
        return service.reporte();
    }
}