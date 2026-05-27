package org.example.ColeccionesDisenoProfesional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestorEmpleados {
    private static final GestorEmpleados INSTANCIA = new GestorEmpleados();

    private final ArrayList<Empleado> empleados;
    private final HashMap<Integer, Empleado> empleadosPorId;
    private final ListaEnlazada<Empleado> empleadosEnlazados;
    private final EmpleadoFactory factory;

    private GestorEmpleados() {
        empleados = new ArrayList<>();
        empleadosPorId = new HashMap<>();
        empleadosEnlazados = new ListaEnlazada<>();
        factory = new EmpleadoFactory();
    }

    public static GestorEmpleados getInstancia() {
        return INSTANCIA;
    }

    public boolean anadirEmpleado(int id, String nombre, String departamento) {
        if (empleadosPorId.containsKey(id)) {
            return false;
        }
        Empleado empleado;
        try {
            empleado = factory.crearEmpleado(id, nombre, departamento);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        empleados.add(empleado);
        empleadosPorId.put(id, empleado);
        empleadosEnlazados.agregar(empleado);
        return true;
    }

    public Empleado buscarPorId(int id) {
        return empleadosPorId.get(id);
    }

    public boolean eliminarEmpleado(int id) {
        Empleado eliminado = empleadosPorId.remove(id);
        if (eliminado == null) {
            return false;
        }
        Iterator<Empleado> it = empleados.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                break;
            }
        }
        empleadosEnlazados.eliminarPrimero(e -> e.getId() == id);
        return true;
    }

    public ArrayList<Empleado> filtrarPorDepartamento(String departamento) {
        ArrayList<Empleado> filtrados = new ArrayList<>();
        if (departamento == null) {
            return filtrados;
        }
        String dep = departamento.trim().toLowerCase();
        for (Empleado empleado : empleados) {
            if (empleado.getDepartamento().toLowerCase().equals(dep)) {
                filtrados.add(empleado);
            }
        }
        return filtrados;
    }

    public void listarEmpleados() {
        if (empleadosEnlazados.getTamano() == 0) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        empleadosEnlazados.recorrer(System.out::println);
    }
}


