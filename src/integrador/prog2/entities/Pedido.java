package integrador.prog2.entities;

import integrador.prog2.enums.Estado;
import integrador.prog2.enums.FormaPago;
import integrador.prog2.interfaces.Calculable;
import integrador.prog2.exceptions.StockInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;

    private Usuario usuario;

    private List<DetallePedido> detalles;

    public Pedido(Usuario usuario, FormaPago formaPago) {
        super();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
    }

    public void addDetallePedido(int cantidad,
                                 Double subtotal,
                                 Producto producto)
            throws StockInvalidoException {

        if (cantidad <= 0) {
            throw new StockInvalidoException(
                    "La cantidad debe ser mayor a cero."
            );
        }

        if (producto.getStock() < cantidad) {
            throw new StockInvalidoException(
                    "Stock insuficiente para el producto: "
                            + producto.getNombre()
            );
        }

        // El subtotal se calcula automáticamente en DetallePedido
        DetallePedido detalle = new DetallePedido(cantidad, producto);

        detalles.add(detalle);

        // Descontar stock
        producto.setStock(
                producto.getStock() - cantidad
        );

        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(
            Producto producto) {

        for (DetallePedido detalle : detalles) {

            if (detalle.getProducto().getId()
                    .equals(producto.getId())) {

                return detalle;
            }

        }

        return null;
    }

    public void deleteDetallePedidoByProducto(
            Producto producto) {

        DetallePedido detalle =
                findDetallePedidoByProducto(producto);

        if (detalle != null) {

            // devolver stock
            producto.setStock(
                    producto.getStock()
                            + detalle.getCantidad()
            );

            detalles.remove(detalle);

            calcularTotal();
        }
    }

    @Override
    public void calcularTotal() {

        total = 0;

        for (DetallePedido detalle : detalles) {

            total += detalle.getSubtotal();

        }
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {

        return "Pedido{" +
                "id=" + id +
                ", usuario=" +
                usuario.getNombre() + " " +
                usuario.getApellido() +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", formaPago=" + formaPago +
                ", total=" + total +
                '}';
    }
}