package org.example.ColeccionesDisenoProfesional;

public class EmpleadoFactory {
    public Empleado crearEmpleado(int id, String nombre, String departamento) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor que 0");
        }
        String nombreLimpio = normalizar(nombre, "SinNombre");
        String departamentoLimpio = normalizar(departamento, "SinDepartamento");
        return new Empleado(id, nombreLimpio, departamentoLimpio);
    }

    private String normalizar(String valor, String porDefecto) {
        if (valor == null) {
            return porDefecto;
        }
        String limpio = valor.trim();
        return limpio.isEmpty() ? porDefecto : limpio;
    }
}


