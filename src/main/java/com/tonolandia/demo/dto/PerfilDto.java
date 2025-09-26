package com.tonolandia.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PerfilDto {
    @NotBlank(message = "{perfil.rol.notblank}")
    private String rol;

    @Size(max = 200, message = "{perfil.bio.size}")
    private String bio;
}
