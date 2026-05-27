package org.example.ColeccionesDisenoProfesional;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;

    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamano++;
    }

    public boolean eliminarPrimero(Predicate<T> criterio) {
        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;
        while (actual != null) {
            if (criterio.test(actual.valor)) {
                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                if (actual == cola) {
                    cola = anterior;
                }
                tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public void recorrer(Consumer<T> accion) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            accion.accept(actual.valor);
            actual = actual.siguiente;
        }
    }

    public int getTamano() {
        return tamano;
    }
}


