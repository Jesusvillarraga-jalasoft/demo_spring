package com.tonolandia.demo.repository.producto;

import com.tonolandia.demo.dto.ProductoResumenDto;
import com.tonolandia.demo.entity.Producto;
import com.tonolandia.demo.entity.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {

    // Buscar productos por nombre (ignora mayúsculas/minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos con precio mayor que un valor
    List<Producto> findByPrecioGreaterThan(Double precio);

    // Buscar productos con precio en un rango
    List<Producto> findByPrecioBetween(Double min, Double max);

    // Buscar productos por parte del código del ID compuesto
    List<Producto> findByIdCodigo(String codigo);

    // Buscar productos por parte del lote del ID compuesto
    List<Producto> findByIdLote(String lote);

    @Query("SELECT new com.demo.dto.ProductoResumenDto(p.id.codigo, p.id.lote, p.precio) " +
            "FROM Producto p WHERE p.precio > :precioMin")
    List<ProductoResumenDto> buscarProductosCaros(@Param("precioMin") Double precioMin);
}
