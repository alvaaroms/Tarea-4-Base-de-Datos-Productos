package org.example.BaseDatosProductos;

import java.util.List;
import java.util.Scanner;

public class MenuProductos {
    public static void main(String[] args) {
        Database.initialize();
        ProductoDao dao = new ProductoDao();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("=== SISTEMA DE PRODUCTOS (JDBC + ORACLE) ===");

        while (!salir) {
            System.out.println("\nQue deseas hacer?");
            System.out.println("1. Insertar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Modificar precio");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");
            System.out.print("Opcion: ");

            int opcion = leerEntero(scanner);
            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = leerEntero(scanner);
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = leerDouble(scanner);
                    System.out.print("Vendedor: ");
                    String vendedor = scanner.nextLine();
                    boolean insertado = dao.insertar(new Producto(id, nombre, precio, vendedor));
                    System.out.println(insertado ? "Producto insertado." : "No se pudo insertar.");
                    break;
                case 2:
                    List<Producto> productos = dao.listar();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos.");
                    } else {
                        for (Producto p : productos) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    List<Producto> encontrados = dao.buscarPorNombre(nombreBuscar);
                    if (encontrados.isEmpty()) {
                        System.out.println("No se encontraron productos.");
                    } else {
                        for (Producto p : encontrados) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 4:
                    System.out.print("ID del producto: ");
                    int idActualizar = leerEntero(scanner);
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = leerDouble(scanner);
                    boolean actualizado = dao.actualizarPrecio(idActualizar, nuevoPrecio);
                    System.out.println(actualizado ? "Precio actualizado." : "No se actualizo el precio.");
                    break;
                case 5:
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = leerEntero(scanner);
                    boolean eliminado = dao.eliminar(idEliminar);
                    System.out.println(eliminado ? "Producto eliminado." : "No se elimino el producto.");
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        }
        scanner.close();
    }

    private static int leerEntero(Scanner scanner) {
        while (true) {
            String linea = scanner.nextLine();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Numero no valido, intenta de nuevo: ");
            }
        }
    }

    private static double leerDouble(Scanner scanner) {
        while (true) {
            String linea = scanner.nextLine();
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.print("Precio no valido, intenta de nuevo: ");
            }
        }
    }
}


