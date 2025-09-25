package com.tonolandia.demo.controller;

import com.tonolandia.demo.dto.PerfilDto;
import com.tonolandia.demo.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @Operation(summary = "Crear un perfil", description = "Crea un nuevo perfil con un nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil creado correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilDto crearPerfil(@RequestBody PerfilDto dto) {
        return perfilService.crearPerfil(dto);
    }

    @Operation(summary = "Listar perfiles", description = "Obtiene todos los perfiles registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado devuelto correctamente")
    })
    @GetMapping
    public List<PerfilDto> listarPerfiles() {
        return perfilService.listarPerfiles();
    }
}
