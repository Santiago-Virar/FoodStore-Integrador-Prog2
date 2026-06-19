package integrador.prog2.services;

import integrador.prog2.entities.Producto;
import integrador.prog2.exceptions.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos;

    public ProductoService() {
        this.productos = new ArrayList<>();
    }

    // LISTAR SOLO PRODUCTOS ACTIVOS
    public List<Producto> listar() {

        List<Producto> activos = new ArrayList<>();

        for (Producto producto : productos) {

            if (!producto.isEliminado()) {
                activos.add(producto);
            }

        }

        return activos;
    }

    // CREAR
    public void crear(Producto producto) {

        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        productos.add(producto);
    }

    // BUSCAR POR ID
    public Producto buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Producto producto : productos) {

            if (producto.getId().equals(id)
                    && !producto.isEliminado()) {

                return producto;
            }

        }

        throw new EntidadNoEncontradaException(
                "Producto no encontrado."
        );
    }

    // EDITAR
    public void editar(Long id,
                       String nombre,
                       double precio,
                       String descripcion,
                       int stock,
                       String imagen,
                       boolean disponible)
            throws EntidadNoEncontradaException {

        Producto producto = buscarPorId(id);

        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setImagen(imagen);
        producto.setDisponible(disponible);
    }

    // ELIMINAR (BAJA LÓGICA)
    public void eliminar(Long id)
            throws EntidadNoEncontradaException {

        Producto producto = buscarPorId(id);

        producto.setEliminado(true);
    }
}