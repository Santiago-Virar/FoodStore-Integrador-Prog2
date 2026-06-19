package integrador.prog2.services;

import integrador.prog2.entities.Categoria;
import integrador.prog2.exceptions.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    private List<Categoria> categorias;

    public CategoriaService() {
        this.categorias = new ArrayList<>();
    }

    // LISTAR SOLO LAS CATEGORÍAS ACTIVAS
    public List<Categoria> listar() {

        List<Categoria> activas = new ArrayList<>();

        for (Categoria categoria : categorias) {

            if (!categoria.isEliminado()) {
                activas.add(categoria);
            }

        }

        return activas;
    }

    // CREAR
    public void crear(Categoria categoria) {

        for (Categoria cat : categorias) {

            if (!cat.isEliminado() &&
                    cat.getNombre().equalsIgnoreCase(categoria.getNombre())) {

                throw new IllegalArgumentException(
                        "Ya existe una categoría con ese nombre."
                );
            }
        }

        categorias.add(categoria);
    }

    // BUSCAR POR ID
    public Categoria buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Categoria categoria : categorias) {

            if (categoria.getId().equals(id)
                    && !categoria.isEliminado()) {

                return categoria;
            }

        }

        throw new EntidadNoEncontradaException(
                "Categoría no encontrada."
        );
    }

    // EDITAR
    public void editar(Long id,
                       String nombre,
                       String descripcion)
            throws EntidadNoEncontradaException {

        Categoria categoria = buscarPorId(id);

        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }

    // ELIMINAR (BAJA LÓGICA)
    public void eliminar(Long id)
            throws EntidadNoEncontradaException {

        Categoria categoria = buscarPorId(id);

        categoria.setEliminado(true);
    }

}