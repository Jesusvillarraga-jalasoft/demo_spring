package com.tonolandia.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    @NotBlank
    private String nombre;

    @Email @NotBlank
    private String email;

    @NotNull
    private Long perfilId;
}
