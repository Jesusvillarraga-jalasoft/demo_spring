package com.tonolandia.demo.service.producto;

import com.tonolandia.demo.dto.ProductoCreateDto;
import com.tonolandia.demo.dto.ProductoResumenDto;
import com.tonolandia.demo.entity.Producto;
import com.tonolandia.demo.entity.ProductoId;
import com.tonolandia.demo.repository.producto.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listarProductos() { return repo.findAll(); }

    public Producto buscarPorId(String codigo, String lote) {
        ProductoId id = new ProductoId();
        id.setCodigo(codigo);
        id.setLote(lote);
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto crearProducto(ProductoCreateDto dto) {
        ProductoId id = new ProductoId();
        id.setCodigo(dto.codigo());
        id.setLote(dto.lote());
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(dto.nombre());
        p.setPrecio(dto.precio());
        return repo.save(p);
    }

    public Producto actualizarProducto(String codigo, String lote, ProductoCreateDto dto) {
        Producto existente = buscarPorId(codigo, lote);
        existente.setNombre(dto.nombre());
        existente.setPrecio(dto.precio());
        return repo.save(existente);
    }

    public void eliminarProducto(String codigo, String lote) {
        ProductoId id = new ProductoId();
        id.setCodigo(codigo);
        id.setLote(lote);
        repo.deleteById(id);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorPrecioMayorA(Double precio) {
        return repo.findByPrecioGreaterThan(precio);
    }

    public List<Producto> buscarPorRangoPrecio(Double min, Double max) {
        return repo.findByPrecioBetween(min, max);
    }

    public List<Producto> buscarPorCodigo(String codigo) {
        return repo.findByIdCodigo(codigo);
    }

    public List<Producto> buscarPorLote(String lote) {
        return repo.findByIdLote(lote);
    }

    public List<ProductoResumenDto> buscarProductosCaros(Double precioMin) {
        return repo.buscarProductosCaros(precioMin);
    }
}
