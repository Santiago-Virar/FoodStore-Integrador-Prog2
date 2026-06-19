package integrador.prog2;

import integrador.prog2.entities.Categoria;
import integrador.prog2.entities.Pedido;
import integrador.prog2.entities.Producto;
import integrador.prog2.entities.Usuario;
import integrador.prog2.enums.FormaPago;
import integrador.prog2.enums.Rol;
import integrador.prog2.exceptions.EntidadNoEncontradaException;
import integrador.prog2.exceptions.MailDuplicadoException;
import integrador.prog2.exceptions.StockInvalidoException;
import integrador.prog2.services.CategoriaService;
import integrador.prog2.services.ProductoService;
import integrador.prog2.services.UsuarioService;

public class MainPruebas {

    public static void main(String[] args) {

        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService();
        UsuarioService usuarioService = new UsuarioService();

        try {

            /*
             * ==========================
             * CATEGORÍAS
             * ==========================
             */

            Categoria pizzas = new Categoria(
                    "Pizzas",
                    "Pizza artesanal"
            );

            Categoria hamburguesas = new Categoria(
                    "Hamburguesas",
                    "Hamburguesas caseras"
            );

            Categoria bebidas = new Categoria(
                    "Bebidas",
                    "Gaseosas y jugos"
            );

            categoriaService.crear(pizzas);
            categoriaService.crear(hamburguesas);
            categoriaService.crear(bebidas);

            categoriaService.editar(
                    hamburguesas.getId(),
                    "Hamburguesas Premium",
                    "Hamburguesas gourmet"
            );

            categoriaService.eliminar(
                    pizzas.getId()
            );

            System.out.println("\n=== CATEGORÍAS ===");

            for (Categoria categoria : categoriaService.listar()) {
                System.out.println(categoria);
            }

            /*
             * ==========================
             * PRODUCTOS
             * ==========================
             */

            Producto hamburguesa = new Producto(
                    "Hamburguesa Doble",
                    9500,
                    "Con cheddar",
                    15,
                    "burger.jpg",
                    true,
                    hamburguesas
            );

            Producto coca = new Producto(
                    "Coca Cola",
                    3500,
                    "500 ml",
                    30,
                    "coca.jpg",
                    true,
                    bebidas
            );

            productoService.crear(hamburguesa);
            productoService.crear(coca);

            productoService.editar(
                    coca.getId(),
                    "Coca Cola Zero",
                    3800,
                    "500 ml sin azúcar",
                    25,
                    "cocazero.jpg",
                    true
            );

            productoService.eliminar(
                    hamburguesa.getId()
            );

            System.out.println("\n=== PRODUCTOS ===");

            for (Producto producto : productoService.listar()) {
                System.out.println(producto);
            }

            /*
             * ==========================
             * USUARIOS
             * ==========================
             */

            Usuario santiago = new Usuario(
                    "Santiago",
                    "Virar",
                    "santiago@gmail.com",
                    "2634000000",
                    "1234",
                    Rol.ADMIN
            );

            Usuario juan = new Usuario(
                    "Juan",
                    "Perez",
                    "juan@gmail.com",
                    "2634111111",
                    "5678",
                    Rol.USUARIO
            );

            usuarioService.crear(santiago);
            usuarioService.crear(juan);

            System.out.println("\n=== USUARIOS INICIALES ===");

            for (Usuario usuario : usuarioService.listar()) {
                System.out.println(usuario);
            }

            usuarioService.editar(
                    juan.getId(),
                    "Juan Carlos",
                    "Perez",
                    "juancarlos@gmail.com",
                    "2634222222",
                    "9999"
            );

            usuarioService.eliminar(
                    santiago.getId()
            );

            System.out.println("\n=== USUARIOS FINALES ===");

            for (Usuario usuario : usuarioService.listar()) {
                System.out.println(usuario);
            }

            /*
             * ==========================
             * PEDIDOS
             * ==========================
             */

            System.out.println("\n=== PEDIDOS ===");

            Pedido pedido = new Pedido(
                    juan,
                    FormaPago.EFECTIVO
            );

            pedido.addDetallePedido(
                    2,
                    0.0,
                    coca
            );

            System.out.println("\n=== DETALLES DEL PEDIDO ===");

            for (var detalle : pedido.getDetalles()) {
                System.out.println(detalle);
            }

            System.out.println("\n=== PEDIDO CREADO ===");

            System.out.println(pedido);

            System.out.println("\n=== ELIMINANDO DETALLE ===");

            pedido.deleteDetallePedidoByProducto(coca);

            System.out.println("Cantidad de detalles: "
                    + pedido.getDetalles().size());

            System.out.println("Total del pedido: "
                    + pedido.getTotal());

        } catch (EntidadNoEncontradaException |
                 MailDuplicadoException |
                 StockInvalidoException |
                 IllegalArgumentException e) {

            System.out.println(e.getMessage());

        }

    }
}