# Integrador final de Programacion II - Sistema Food Store

Integrador Final de Programación 2 desarrollado en Java utilizando Programación Orientada a Objetos.

## Integrantes

- Santiago Virar
- [Nombre compañero 2]
- [Nombre compañero 3]

## Profesor

Ramiro Hualpa

## Descripción del proyecto

Food Store es una aplicación desarrollada en Java que permite gestionar categorías, productos, usuarios y pedidos mediante un menú interactivo. El sistema implementa conceptos fundamentales de Programación Orientada a Objetos, utilizando herencia, interfaces, excepciones personalizadas y colecciones.

## Funcionalidades implementadas

- CRUD de Categorías
- CRUD de Productos
- CRUD de Usuarios
- CRUD de Pedidos
- Baja lógica
- Manejo de excepciones personalizadas
- Control de stock
- Cálculo automático de totales
- Menú interactivo

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- Git y GitHub
- Programación Orientada a Objetos
- Colecciones (List y ArrayList)
- Interfaces
- Enums
- Excepciones

## Repositorio GitHub

https://github.com/Santiago-Virar/FoodStore-Integrador-Prog2.git

## Video demostrativo

https://drive.google.com/drive/folders/1KFe4c7YSQZV9_HQHctXIobEmalqJ9UVL?usp=drive_link

## ¿Cómo funciona el programa?

Al ejecutar la aplicación se muestra un menú principal desde el cual el usuario puede acceder a las distintas funcionalidades del sistema.

```text
=== FOOD STORE ===
1. Categorías
2. Productos
3. Usuarios
4. Pedidos
0. Salir
```

### Gestión de Categorías
Permite administrar las categorías de los productos mediante operaciones CRUD:
- Listar categorías existentes.
- Crear nuevas categorías.
- Modificar categorías.
- Eliminar categorías mediante baja lógica.

### Gestión de Productos
Permite administrar los productos disponibles en el sistema:
- Listar productos.
- Crear productos asociándolos a una categoría existente.
- Modificar sus datos (nombre, precio, descripción, stock, etc.).
- Eliminar productos mediante baja lógica.

### Gestión de Usuarios
Permite gestionar los usuarios del sistema:
- Listar usuarios registrados.
- Crear nuevos usuarios indicando sus datos y rol.
- Editar información de usuarios existentes.
- Eliminar usuarios mediante baja lógica.
- Validar que no existan dos usuarios con el mismo correo electrónico.

### Gestión de Pedidos
Permite registrar pedidos realizados por los usuarios:
- Seleccionar un usuario previamente registrado.
- Elegir la forma de pago (tarjeta, transferencia o efectivo).
- Agregar uno o varios productos al pedido.
- Indicar la cantidad deseada de cada producto.
- Validar que exista stock suficiente antes de confirmar la operación.
- Descontar automáticamente el stock de los productos vendidos.
- Calcular automáticamente el total del pedido.
- Listar todos los pedidos creados junto con sus detalles.

### Validaciones implementadas

El sistema incorpora distintas validaciones para garantizar la integridad de los datos:

- No permite crear usuarios con correos electrónicos duplicados.
- No permite registrar productos con precio o stock negativo.
- No permite crear detalles de pedido con cantidades menores o iguales a cero.
- No permite generar pedidos cuando el stock disponible es insuficiente.
- Informa mediante excepciones personalizadas cuando una entidad no es encontrada.