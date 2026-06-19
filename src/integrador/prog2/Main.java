package integrador.prog2;

import integrador.prog2.entities.Categoria;
import integrador.prog2.entities.Producto;
import integrador.prog2.exceptions.EntidadNoEncontradaException;
import integrador.prog2.services.CategoriaService;
import integrador.prog2.services.ProductoService;
import integrador.prog2.entities.Usuario;
import integrador.prog2.enums.Rol;
import integrador.prog2.exceptions.MailDuplicadoException;
import integrador.prog2.services.UsuarioService;
import integrador.prog2.entities.Pedido;
import integrador.prog2.entities.DetallePedido;
import integrador.prog2.enums.FormaPago;
import integrador.prog2.exceptions.StockInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService();
        UsuarioService usuarioService = new UsuarioService();
        List<Pedido> pedidos = new ArrayList<>();

        int opcion;

        do {

            System.out.println("\n=== FOOD STORE ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    menuCategorias(scanner, categoriaService);
                    break;

                case 2:
                    menuProductos(scanner, productoService, categoriaService);
                    break;

                case 3:
                    menuUsuarios(scanner, usuarioService);
                    break;

                case 4:
                    menuPedidos(
                            scanner,
                            pedidos,
                            usuarioService,
                            productoService
                    );
                   break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void menuCategorias(Scanner scanner,
                                       CategoriaService categoriaService) {

        int opcion;

        do {

            System.out.println("\n=== CATEGORÍAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcion) {

                    case 1:

                        System.out.println("\n=== LISTADO DE CATEGORÍAS ===");

                        if (categoriaService.listar().isEmpty()) {

                            System.out.println("No hay categorías cargadas.");

                        } else {

                            for (Categoria categoria : categoriaService.listar()) {
                                System.out.println(categoria);
                            }

                        }

                        break;

                    case 2:

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Descripción: ");
                        String descripcion = scanner.nextLine();

                        categoriaService.crear(
                                new Categoria(nombre, descripcion)
                        );

                        System.out.println("Categoría creada correctamente.");

                        break;

                    case 3:

                        for (Categoria categoria : categoriaService.listar()) {
                            System.out.println(categoria);
                        }

                        System.out.print("ID a editar: ");
                        Long idEditar =
                                Long.parseLong(scanner.nextLine());

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre =
                                scanner.nextLine();

                        System.out.print("Nueva descripción: ");
                        String nuevaDescripcion =
                                scanner.nextLine();

                        categoriaService.editar(
                                idEditar,
                                nuevoNombre,
                                nuevaDescripcion
                        );

                        System.out.println("Categoría editada correctamente.");

                        break;

                    case 4:

                        for (Categoria categoria : categoriaService.listar()) {
                            System.out.println(categoria);
                        }

                        System.out.print("ID a eliminar: ");
                        Long idEliminar =
                                Long.parseLong(scanner.nextLine());

                        categoriaService.eliminar(idEliminar);

                        System.out.println("Categoría eliminada correctamente.");

                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (EntidadNoEncontradaException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private static void menuProductos(Scanner scanner,
                                      ProductoService productoService,
                                      CategoriaService categoriaService) {

        int opcion;

        do {

            System.out.println("\n=== PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcion) {

                    case 1:

                        System.out.println("\n=== LISTADO DE PRODUCTOS ===");

                        if (productoService.listar().isEmpty()) {

                            System.out.println("No hay productos cargados.");

                        } else {

                            for (Producto producto : productoService.listar()) {
                                System.out.println(producto);
                            }

                        }

                        break;

                    case 2:

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Precio: ");
                        double precio =
                                Double.parseDouble(scanner.nextLine());

                        System.out.print("Descripción: ");
                        String descripcion =
                                scanner.nextLine();

                        System.out.print("Stock: ");
                        int stock =
                                Integer.parseInt(scanner.nextLine());

                        System.out.print("Imagen: ");
                        String imagen =
                                scanner.nextLine();

                        System.out.print("Disponible (true/false): ");
                        boolean disponible =
                                Boolean.parseBoolean(scanner.nextLine());

                        System.out.println("\nCategorías disponibles:");

                        for (Categoria categoria : categoriaService.listar()) {
                            System.out.println(categoria);
                        }

                        System.out.print("ID de categoría: ");
                        Long idCategoria =
                                Long.parseLong(scanner.nextLine());

                        Categoria categoria =
                                categoriaService.buscarPorId(idCategoria);

                        productoService.crear(
                                new Producto(
                                        nombre,
                                        precio,
                                        descripcion,
                                        stock,
                                        imagen,
                                        disponible,
                                        categoria
                                )
                        );

                        System.out.println("Producto creado correctamente.");

                        break;

                    case 3:

                        for (Producto producto : productoService.listar()) {
                            System.out.println(producto);
                        }

                        System.out.print("ID a editar: ");
                        Long idEditar =
                                Long.parseLong(scanner.nextLine());

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre =
                                scanner.nextLine();

                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio =
                                Double.parseDouble(scanner.nextLine());

                        System.out.print("Nueva descripción: ");
                        String nuevaDescripcion =
                                scanner.nextLine();

                        System.out.print("Nuevo stock: ");
                        int nuevoStock =
                                Integer.parseInt(scanner.nextLine());

                        System.out.print("Nueva imagen: ");
                        String nuevaImagen =
                                scanner.nextLine();

                        System.out.print("Disponible (true/false): ");
                        boolean nuevaDisponibilidad =
                                Boolean.parseBoolean(scanner.nextLine());

                        productoService.editar(
                                idEditar,
                                nuevoNombre,
                                nuevoPrecio,
                                nuevaDescripcion,
                                nuevoStock,
                                nuevaImagen,
                                nuevaDisponibilidad
                        );

                        System.out.println("Producto editado correctamente.");

                        break;

                    case 4:

                        for (Producto producto : productoService.listar()) {
                            System.out.println(producto);
                        }

                        System.out.print("ID a eliminar: ");
                        Long idEliminar =
                                Long.parseLong(scanner.nextLine());

                        productoService.eliminar(idEliminar);

                        System.out.println("Producto eliminado correctamente.");

                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (EntidadNoEncontradaException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }
    private static void menuUsuarios(Scanner scanner,
                                     UsuarioService usuarioService) {

        int opcion;

        do {

            System.out.println("\n=== USUARIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcion) {

                    case 1:

                        System.out.println("\n=== LISTADO DE USUARIOS ===");

                        if (usuarioService.listar().isEmpty()) {

                            System.out.println("No hay usuarios cargados.");

                        } else {

                            for (Usuario usuario : usuarioService.listar()) {
                                System.out.println(usuario);
                            }

                        }

                        break;

                    case 2:

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();

                        System.out.print("Mail: ");
                        String mail = scanner.nextLine();

                        System.out.print("Celular: ");
                        String celular = scanner.nextLine();

                        System.out.print("Contraseña: ");
                        String contraseña = scanner.nextLine();

                        System.out.println("Roles disponibles:");
                        System.out.println("1. ADMIN");
                        System.out.println("2. USUARIO");

                        System.out.print("Seleccione rol: ");
                        int opcionRol =
                                Integer.parseInt(scanner.nextLine());

                        Rol rol;

                        if (opcionRol == 1) {
                            rol = Rol.ADMIN;
                        } else {
                            rol = Rol.USUARIO;
                        }

                        usuarioService.crear(
                                new Usuario(
                                        nombre,
                                        apellido,
                                        mail,
                                        celular,
                                        contraseña,
                                        rol
                                )
                        );

                        System.out.println("Usuario creado correctamente.");

                        break;

                    case 3:

                        for (Usuario usuario : usuarioService.listar()) {
                            System.out.println(usuario);
                        }

                        System.out.print("ID a editar: ");
                        Long idEditar =
                                Long.parseLong(scanner.nextLine());

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre =
                                scanner.nextLine();

                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido =
                                scanner.nextLine();

                        System.out.print("Nuevo mail: ");
                        String nuevoMail =
                                scanner.nextLine();

                        System.out.print("Nuevo celular: ");
                        String nuevoCelular =
                                scanner.nextLine();

                        System.out.print("Nueva contraseña: ");
                        String nuevaContraseña =
                                scanner.nextLine();

                        usuarioService.editar(
                                idEditar,
                                nuevoNombre,
                                nuevoApellido,
                                nuevoMail,
                                nuevoCelular,
                                nuevaContraseña
                        );

                        System.out.println("Usuario editado correctamente.");

                        break;

                    case 4:

                        for (Usuario usuario : usuarioService.listar()) {
                            System.out.println(usuario);
                        }

                        System.out.print("ID a eliminar: ");
                        Long idEliminar =
                                Long.parseLong(scanner.nextLine());

                        usuarioService.eliminar(idEliminar);

                        System.out.println("Usuario eliminado correctamente.");

                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opción inválida.");

                }

            } catch (EntidadNoEncontradaException |
                     MailDuplicadoException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            }

        } while (opcion != 0);
    }
    private static void menuPedidos(Scanner scanner,
                                    List<Pedido> pedidos,
                                    UsuarioService usuarioService,
                                    ProductoService productoService) {

        int opcion;

        do {

            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcion) {

                    case 1:

                        System.out.println("\n=== LISTADO DE PEDIDOS ===");

                        if (pedidos.isEmpty()) {

                            System.out.println("No hay pedidos cargados.");

                        } else {

                            for (Pedido pedido : pedidos) {

                                System.out.println(pedido);

                                for (DetallePedido detalle : pedido.getDetalles()) {

                                    System.out.println("   " + detalle);

                                }

                            }

                        }

                        break;

                    case 2:

                        if (usuarioService.listar().isEmpty()) {

                            System.out.println("Debe existir al menos un usuario.");

                            break;
                        }

                        if (productoService.listar().isEmpty()) {

                            System.out.println("Debe existir al menos un producto.");

                            break;
                        }

                        System.out.println("\n=== USUARIOS DISPONIBLES ===");

                        for (Usuario usuario : usuarioService.listar()) {

                            System.out.println(usuario);
                        }

                        System.out.print("ID del usuario: ");
                        Long idUsuario =
                                Long.parseLong(scanner.nextLine());

                        Usuario usuario =
                                usuarioService.buscarPorId(idUsuario);

                        System.out.println("\nFORMAS DE PAGO");
                        System.out.println("1. TARJETA");
                        System.out.println("2. TRANSFERENCIA");
                        System.out.println("3. EFECTIVO");

                        System.out.print("Seleccione una opción: ");
                        int opcionPago =
                                Integer.parseInt(scanner.nextLine());

                        FormaPago formaPago;

                        switch (opcionPago) {

                            case 1:
                                formaPago = FormaPago.TARJETA;
                                break;

                            case 2:
                                formaPago = FormaPago.TRANSFERENCIA;
                                break;

                            default:
                                formaPago = FormaPago.EFECTIVO;
                        }

                        Pedido pedido =
                                new Pedido(usuario, formaPago);

                        String continuar;

                        do {

                            System.out.println("\n=== PRODUCTOS DISPONIBLES ===");

                            for (Producto producto : productoService.listar()) {

                                System.out.println(producto);
                            }

                            System.out.print("ID del producto: ");
                            Long idProducto =
                                    Long.parseLong(scanner.nextLine());

                            Producto producto =
                                    productoService.buscarPorId(idProducto);

                            System.out.print("Cantidad: ");
                            int cantidad =
                                    Integer.parseInt(scanner.nextLine());

                            pedido.addDetallePedido(
                                    cantidad,
                                    0.0,
                                    producto
                            );

                            System.out.print("¿Agregar otro producto? (S/N): ");
                            continuar =
                                    scanner.nextLine();

                        } while (continuar.equalsIgnoreCase("S"));

                        pedidos.add(pedido);

                        System.out.println("\n=== PEDIDO CREADO ===");

                        System.out.println(pedido);

                        System.out.println("\nDETALLES:");

                        for (DetallePedido detalle : pedido.getDetalles()) {

                            System.out.println(detalle);

                        }

                        break;

                    case 0:
                        break;

                    default:

                        System.out.println("Opción inválida.");

                }

            } catch (EntidadNoEncontradaException |
                     StockInvalidoException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            }

        } while (opcion != 0);
    }
}