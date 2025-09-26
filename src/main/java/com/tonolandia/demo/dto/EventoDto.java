package com.tonolandia.demo.dto;

import com.tonolandia.demo.annotation.FechaFinPosteriorAInicio;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@FechaFinPosteriorAInicio(inicio = "inicio", fin = "fin", message = "{rango.fechas.invalido}")
@Setter
@Getter
@NoArgsConstructor
public class EventoDto {

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fin;
}
