package integrador.prog2.entities;

public class DetallePedido extends Base {

    private int cantidad;
    private double subtotal;

    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = cantidad * producto.getPrecio();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "producto=" + producto.getNombre() +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}