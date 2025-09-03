package com.tonolandia.demo.service;

import com.tonolandia.demo.domain.Contrato;
import com.tonolandia.demo.dto.*;
import com.tonolandia.demo.repository.ContratoRepository;
import com.tonolandia.demo.repository.EjecutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AltaMesaServiceImpl implements AltaMesaService {

    private final ContratoRepository contratos;
    private final EjecutorRepository ejecutores;

    public AltaMesaServiceImpl(ContratoRepository cRepo, EjecutorRepository eRepo) {
        this.contratos = cRepo;
        this.ejecutores = eRepo;
    }

    /**
     * TODO: implementar usando Streams.
     *
     * Subtareas técnicas (checklist):
     * <ul>
     *   <li>[ ] Filtrar contratos cuyo estado sea {@code ABIERTO} y con recompensa mayor o igual a {@code recompensaMin}.</li>
     *   <li>[ ] Ordenar los contratos en orden descendente por recompensa.</li>
     *   <li>[ ] Limitar los resultados a {@code limit}.</li>
     *   <li>[ ] Mapear los contratos a {@link ContratoDto}.</li>
     *   <li>[ ] Devolver la lista como inmutable usando {@code toList()}.</li>
     * </ul>
     */
    @Override
    public List<ContratoDto> topContratos(int recompensaMin, int limit) {
        throw new UnsupportedOperationException("TODO topContratos");
    }

    /**
     * TODO: implementar usando Streams junto con Set/Map.
     *
     * Subtareas técnicas (checklist):
     * <ul>
     *   <li>[ ] Validar el ejecutor.</li>
     *   <li>[ ] Filtrar contratos con estado {@code ABIERTO} que cumplan con la recompensa mínima
     *          y, si existen, con las etiquetas requeridas.</li>
     *   <li>[ ] Priorizar los contratos por recompensa descendente y opcionalmente por "match" de habilidades.</li>
     *   <li>[ ] Tomar hasta {@code maxContratos}.</li>
     *   <li>[ ] Cambiar el estado creando <b>nuevos</b> objetos {@code Contrato} con estado {@code ASIGNADO}.</li>
     *   <li>[ ] Guardar los nuevos objetos (sin mutar los existentes).</li>
     *   <li>[ ] Devolver un response con los IDs asignados y la fecha = {@code Instant.now()}.</li>
     * </ul>
     */
    @Override
    public AsignacionResponse asignar(AsignacionRequest req) {
        throw new UnsupportedOperationException("TODO asignar");
    }

    /**
     * TODO: implementar este método.
     *
     * Subtareas técnicas (checklist):
     * <ul>
     *   <li>[ ] A partir de los IDs, cargar los contratos.</li>
     *   <li>[ ] Crear <b>nuevos</b> objetos con estado {@code CERRADO}
     *          (usar {@code map -> new Contrato(..., Estado.CERRADO)}).</li>
     *   <li>[ ] Guardar todos los contratos usando {@code saveAll}.</li>
     *   <li>[ ] Devolver DTOs como lista inmutable.</li>
     * </ul>
     */
    @Override
    public List<ContratoDto> cerrar(CierreRequest request) {
        throw new UnsupportedOperationException("TODO cerrar");
    }

    /**
     * TODO: implementar este método usando la Stream API.
     *
     * Subtareas técnicas (checklist):
     * <ul>
     *   <li>[ ] Calcular <b>total</b> = tamaño de la colección.</li>
     *   <li>[ ] Agrupar <b>porEstado</b>: Map&lt;Estado, Long&gt;, convirtiendo las llaves a String.</li>
     *   <li>[ ] Agrupar <b>porEtiqueta</b>: aplanar etiquetas y contar ocurrencias.</li>
     *   <li>[ ] Obtener <b>topUbicaciones</b>: aplanar ubicaciones, contar, ordenar en forma descendente
     *          y limitar a 3 resultados, devolviendo una lista de Strings.</li>
     * </ul>
     */
    @Override
    public ReporteContratosDto reporte() {
        throw new UnsupportedOperationException("TODO reporte");
    }

    // Helper: mapping a DTO
    private static ContratoDto toDto(Contrato c) {
        return new ContratoDto(
                c.id(), c.objetivo(), c.recompensa(), c.estado().name(),
                Set.copyOf(c.etiquetas()), List.copyOf(c.ubicaciones())
        );
    }
}