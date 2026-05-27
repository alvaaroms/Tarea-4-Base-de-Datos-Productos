package org.example.BaseDatosProductos;

public class Producto {
    private final int id;
    private final String nombre;
    private final double precio;
    private final String vendedor;

    public Producto(int id, String nombre, double precio, String vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.vendedor = vendedor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getVendedor() {
        return vendedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", vendedor='" + vendedor + '\'' +
                '}';
    }
}


