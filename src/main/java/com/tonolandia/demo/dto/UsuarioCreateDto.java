package com.tonolandia.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para crear usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDto {

    @NotBlank(message = "{usuario.nombre.notblank}")
    @Schema(example = "Ada Lovelace")
    private String nombre;

    @Email(message = "{usuario.email.email}")
    @NotBlank(message = "{usuario.email.email}")
    @Schema(example = "ada@example.com")
    private String email;

    @Min(value = 18, message = "{usuario.edad.min}")
    @Schema(example = "21")
    private Integer edad;
}
