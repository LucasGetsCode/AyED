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
        primero = segundo.ant;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<T>();
        Nodo actual = primero;
        while (actual != null) {
            Nodo nuevoNodo = new Nodo(actual.valor);
            if (nuevaLista.primero == null) {
                nuevaLista.primero = nuevoNodo;
                nuevaLista.ultimo = nuevoNodo;
            } else {
                nuevoNodo.ant = nuevaLista.ultimo;
                nuevaLista.ultimo.sig = nuevoNodo;
                nuevaLista.ultimo = nuevoNodo;
            }
            actual = actual.sig;
        }
        return nuevaLista;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        primero = null;
        ultimo = null;
        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }

    @Override
    public String toString() {
        Nodo actual = primero;
        String res = "[";
        while (actual != null) {
            if (actual != primero) {
                res += ", ";
            }
            res += actual.valor.toString();
            actual = actual.sig;
        }
        res += "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
        Nodo dedito;

        ListaIterador() {
            dedito = primero;
        }

        public boolean haySiguiente() {
            return dedito != null;
        }

        public boolean hayAnterior() {
            if (dedito == null) {
                if (ultimo != null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return dedito.ant != null;
            }
        }

        public T siguiente() {
            T valor = dedito.valor;
            dedito = dedito.sig;
            return valor;
            // return elementos[i];
        }

        public T anterior() {
            // dedito = dedito.ant;
            // return dedito.sig.valor;
            if (dedito == null) {
                dedito = ultimo;
            } else {
                dedito = dedito.ant;
            }
            return dedito.valor;
            // return elementos[i];
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
