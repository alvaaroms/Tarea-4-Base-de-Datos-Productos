package org.example.ColeccionesDisenoProfesional;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuEmpleados {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorEmpleados gestor = GestorEmpleados.getInstancia();
        boolean salir = false;

        System.out.println("=== SISTEMA DE GESTION DE EMPLEADOS ===");

        while (!salir) {
            System.out.println("\nQue deseas hacer?");
            System.out.println("1. Anadir empleado");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Listar empleados");
            System.out.println("5. Filtrar por departamento");
            System.out.println("6. Salir");
            System.out.print("Opcion: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un numero valido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el ID: ");
                    int id = leerEntero(scanner);
                    System.out.print("Introduce el Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el Departamento: ");
                    String dep = scanner.nextLine();
                    boolean anadido = gestor.anadirEmpleado(id, nombre, dep);
                    System.out.println(anadido ? "Empleado anadido." : "No se pudo anadir el empleado.");
                    break;
                case 2:
                    System.out.print("Introduce el ID a buscar: ");
                    int idBusqueda = leerEntero(scanner);
                    Empleado encontrado = gestor.buscarPorId(idBusqueda);
                    System.out.println(encontrado != null ? "Encontrado: " + encontrado : "No se encontro el empleado.");
                    break;
                case 3:
                    System.out.print("Introduce el ID a eliminar: ");
                    int idEliminar = leerEntero(scanner);
                    boolean eliminado = gestor.eliminarEmpleado(idEliminar);
                    System.out.println(eliminado ? "Empleado eliminado." : "No se encontro el empleado.");
                    break;
                case 4:
                    System.out.println("\n--- LISTA DE EMPLEADOS ---");
                    gestor.listarEmpleados();
                    break;
                case 5:
                    System.out.print("Introduce el departamento a filtrar: ");
                    String depFiltro = scanner.nextLine();
                    ArrayList<Empleado> filtrados = gestor.filtrarPorDepartamento(depFiltro);
                    System.out.println("\n--- EMPLEADOS DEL DEPARTAMENTO: " + depFiltro.toUpperCase() + " ---");
                    if (filtrados.isEmpty()) {
                        System.out.println("No hay empleados en ese departamento.");
                    } else {
                        for (Empleado emp : filtrados) {
                            System.out.println(emp);
                        }
                    }
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
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
}


