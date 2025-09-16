package com.tonolandia.demo.controller.producto;

import com.tonolandia.demo.dto.ProductoCreateDto;
import com.tonolandia.demo.dto.ProductoResumenDto;
import com.tonolandia.demo.entity.Producto;
import com.tonolandia.demo.service.producto.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() { return service.listarProductos(); }

    @GetMapping("/{codigo}/{lote}")
    public Producto buscarPorId(
            @Parameter(example = "P123") @PathVariable String codigo,
            @Parameter(example = "L456") @PathVariable String lote) {
        return service.buscarPorId(codigo, lote);
    }

    @PostMapping
    public Producto crear(@RequestBody ProductoCreateDto dto) {
        return service.crearProducto(dto);
    }

    @PutMapping("/{codigo}/{lote}")
    public Producto actualizar(
            @PathVariable String codigo, @PathVariable String lote,
            @RequestBody ProductoCreateDto dto) {
        return service.actualizarProducto(codigo, lote, dto);
    }

    @DeleteMapping("/{codigo}/{lote}") public void eliminar(
            @PathVariable String codigo, @PathVariable String lote) {
        service.eliminarProducto(codigo, lote);
    }

    @Operation(summary = "Buscar productos por nombre (ignora mayúsculas)")
    @GetMapping("/buscar/nombre")
    public List<Producto> buscarPorNombre(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @Operation(summary = "Buscar productos con precio mayor a X")
    @GetMapping("/buscar/precio-mayor")
    public List<Producto> buscarPorPrecioMayorA(@RequestParam Double precio) {
        return service.buscarPorPrecioMayorA(precio);
    }

    @Operation(summary = "Buscar productos en un rango de precios")
    @GetMapping("/buscar/precio-rango")
    public List<Producto> buscarPorRangoPrecio(
            @RequestParam Double min, @RequestParam Double max) {
        return service.buscarPorRangoPrecio(min, max);
    }

    @Operation(summary = "Buscar productos por código (parte del ID compuesto)")
    @GetMapping("/buscar/codigo")
    public List<Producto> buscarPorCodigo(@RequestParam String codigo) {
        return service.buscarPorCodigo(codigo);
    }

    @Operation(summary = "Buscar productos por lote (parte del ID compuesto)")
    @GetMapping("/buscar/lote")
    public List<Producto> buscarPorLote(@RequestParam String lote) {
        return service.buscarPorLote(lote);
    }

    @Operation(summary = "Buscar productos caros (proyección DTO)",
            description = "Devuelve solo código, lote y precio, sin nombre.")
    @GetMapping("/buscar/proyeccion/carros")
    public List<ProductoResumenDto> buscarProductosCaros(
            @Parameter(description = "Precio mínimo", example = "500")
            @RequestParam Double precioMin) {
        return service.buscarProductosCaros(precioMin);
    }
}
