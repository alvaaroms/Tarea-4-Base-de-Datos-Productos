# Tarea-4-Base-de-Datos-Productos
App JDBC de productos con Oracle Database.

## Requisitos
- Java 8+
- Oracle Database en local (XE u otra)
- Driver JDBC Oracle (ojdbc8.jar u ojdbc11.jar)

## Configuracion de BD
1. Editar credenciales y URL en `src/BaseDatosProductos/Database.java`.
2. La tabla se crea automaticamente al ejecutar.

## Ejecucion
- Clase principal: `Main` (redirige a `BaseDatosProductos.MenuProductos`).
- En IntelliJ: abrir `Main.java` y ejecutar.

## Funcionalidades
- Insertar producto
- Listar productos
- Buscar por nombre
- Modificar precio
- Eliminar producto

## Estructura
- `src/BaseDatosProductos/` contiene la app JDBC de productos.

