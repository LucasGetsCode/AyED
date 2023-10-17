package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v) {
            valor = v;
        }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public int longitud() {
        Nodo actual = primero;
        int i = 0;
        while (actual != null) {
            actual = actual.sig;
            i++;
        }
        return i;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.sig = primero;
            primero.ant = nuevo;
            primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.ant = ultimo;
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }
    }

    public T obtener(int i) {
        if (i == 0) {
            return primero.valor;
        }
        T res;
        Nodo segundo = primero.sig;
        while (i > 0) {
            primero = primero.sig;
            i--;
        }
        res = primero.valor;
        primero = segundo.ant;
        return res;
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        Nodo prev = primero;
        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sig;
        }
        if (i == 0) {
            if (actual.sig != null) {
                primero = actual.sig;
                primero.ant = null;
            } else {
                primero = null;
            }

        } else {
            prev.sig = actual.sig;
            if (prev.sig != null) {
                prev.sig.ant = prev;
            }
        }
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo segundo = primero.sig;
        while (indice > 0) {
            primero = primero.sig;
            indice--;
        }
        primero.valor = elem;
        primero = segundo;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> nuevo = new ListaEnlazada<T>();

        return nuevo;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
        int dedito;

        ListaIterador() {
            dedito = 0;
        }

        public boolean haySiguiente() {
            return primero.sig != null;
        }

        public boolean hayAnterior() {
            return primero.ant != null;
        }

        public T siguiente() {
            int i = dedito;
            dedito = dedito++;
            while (i > 0) {
                primero = primero.sig;
                i--;
            }
            return primero.valor;
            // return elementos[i];
        }

        public T anterior() {
            Nodo actual = new Nodo(null);
            int i = dedito;
            dedito = dedito--;
            while (i > 0) {
                primero = primero.ant;
                i--;
            }
            return primero.valor;
            // return elementos[i];
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
