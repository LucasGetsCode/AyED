package ej7;

public class Nodo<T> {
    public T valor;
    public Nodo<T> sig;
    public Nodo<T> ant;
    public Nodo<T> padre;

    public Nodo(T valor) {
        this.valor = valor;
        this.sig = null;
        this.ant = null;
        this.padre = null;
    }
}
