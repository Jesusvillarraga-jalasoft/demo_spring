package com.tonolandia.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UsuarioCreateDto {
    @NotBlank(message = "{usuario.nombre.notblank}")
    @Size(min = 3, message = "{usuario.nombre.size}")
    private String nombre;

    @NotBlank
    @Email(message = "{usuario.email.email}")
    private String email;

    @NotNull
    @Min(value = 18, message = "{usuario.edad.min}")
    private Integer edad;

    @NotNull
    @PastOrPresent(message = "{usuario.fechaRegistro.pastOrPresent}")
    private LocalDate fechaRegistro;

    @Valid
    @NotNull
    private PerfilDto perfil;
}
