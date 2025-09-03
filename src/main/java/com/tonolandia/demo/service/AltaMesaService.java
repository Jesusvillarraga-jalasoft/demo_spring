package com.tonolandia.demo.service;

import com.tonolandia.demo.dto.*;

import java.util.List;

public interface AltaMesaService {

    List<ContratoDto> topContratos(int recompensaMin, int limit);

    AsignacionResponse asignar(AsignacionRequest request);

    List<ContratoDto> cerrar(CierreRequest request);

    ReporteContratosDto reporte();
}