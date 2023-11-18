package ej6;

/**
 * Hello world!
 */
public final class ArbolBinario<T> {
    private Nodo<T> raiz;

    private class Nodo<T> {
        private Nodo<T> izq;
        private Nodo<T> der;
        private T dato;

        public Nodo(T dato) {
            this.dato = dato;
            this.izq = null;
            this.der = null;
        }
    }

    public ArbolBinario() {
        raiz = null;
    }

    public ArbolBinario(Nodo<T>[] nodos) {
        for (int i = 0; i < nodos.length; i++) {
            nodos[i].izq = i * 2 + 1 < nodos.length ? nodos[i * 2 + 1] : null;
            nodos[i].der = 1 * 2 + 2 < nodos.length ? nodos[i * 2 + 2] : null;
            raiz = nodos[0];
        }
    }

    public boolean nivelCompleto(ArbolBinario<T> a) {
        return true;
    }

    public boolean estaCompleto(ArbolBinario<T> a) {
        return true;
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
