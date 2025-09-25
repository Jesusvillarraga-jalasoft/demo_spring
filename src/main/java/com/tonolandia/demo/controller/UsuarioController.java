package com.tonolandia.demo.controller;

import com.tonolandia.demo.dto.UsuarioDto;
import com.tonolandia.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;


    // ===== ModelMapper =====
    @Operation(summary = "Listar usuarios (ModelMapper)", description = "Obtiene todos los usuarios mapeados con ModelMapper")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios devuelto correctamente")
    })
    @GetMapping("/modelmapper")
    public List<UsuarioDto> listarModelMapper() {
        return usuarioService.listarConModelMapper();
    }


    @Operation(summary = "Crear usuario (ModelMapper)", description = "Crea un nuevo usuario usando ModelMapper para mapear DTO ↔ Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en el request")
    })
    @PostMapping("/modelmapper")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto crearModelMapper(@Valid @RequestBody UsuarioDto dto) {
        return usuarioService.crearConModelMapper(dto);
    }


    // ===== MapStruct =====
    @Operation(summary = "Listar usuarios (MapStruct)", description = "Obtiene todos los usuarios mapeados con MapStruct")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios devuelto correctamente")
    })
    @GetMapping("/mapstruct")
    public List<UsuarioDto> listarMapStruct() {
        return usuarioService.listarConMapStruct();
    }


    @Operation(summary = "Crear usuario (MapStruct)", description = "Crea un nuevo usuario usando MapStruct para mapear DTO ↔ Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en el request")
    })
    @PostMapping("/mapstruct")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto crearMapStruct(@Valid @RequestBody UsuarioDto dto) {
        return usuarioService.crearConMapStruct(dto);
    }
}
