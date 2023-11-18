package ej6;

public class Nodo<T> {
    public Nodo<T> izq;
    public Nodo<T> der;
    public T dato;

    public Nodo(T dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
}