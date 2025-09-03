package com.tonolandia.demo.api;

import com.tonolandia.demo.dto.ContratoDto;
import com.tonolandia.demo.service.AltaMesaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final AltaMesaService service;
    public ContratoController(AltaMesaService service) { this.service = service; }

    /**
     * Endpoint GET /api/contratos/top?min=100000&limit=3
     *
     * Obtiene los contratos con valor superior a un mínimo, limitados a un número específico.
     *
     * @param min   valor mínimo del contrato (por defecto 100000)
     * @param limit número máximo de contratos a devolver (por defecto 3)
     * @return lista de {@link ContratoDto} con los contratos filtrados
     */
    @GetMapping("/top")
    public List<ContratoDto> top(@RequestParam(name="min", defaultValue="100000") int min,
                                 @RequestParam(name="limit", defaultValue="3") int limit) {
        return service.topContratos(min, limit);
    }
}