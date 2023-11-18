package ej6;

/**
 * Hello world!
 */
public final class ArbolBinario<T> {
    private Nodo<T> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public ArbolBinario(Nodo<T>[] nodos) {
        for (int i = 0; i < nodos.length; i++) {
            nodos[i].izq = i * 2 + 1 < nodos.length ? nodos[i * 2 + 1] : null;
            nodos[i].der = i * 2 + 2 < nodos.length ? nodos[i * 2 + 2] : null;
            raiz = nodos[0];
        }
    }

    public boolean nivelCompleto(int n) { // O(2^(n))
        return nivelCompleto(raiz, n);
    }

    private boolean nivelCompleto(Nodo<T> nodo, int n) {
        if (n == 0) {
            return true;
        } else {
            if (nodo == null) {
                return false;
            } else {
                return nivelCompleto(nodo.izq, n - 1) && nivelCompleto(nodo.der, n - 1);
            }
        }
    }

    public boolean estaCompleto() { // O(2^altura)
        int nivel = raiz == null ? 1 : 0;
        Nodo<T> actual = raiz;
        while (actual != null) {
            nivel++;
            actual = actual.izq;
        }
        return nivelCompleto(nivel) && !hayCaminoDeAltura(raiz, nivel + 1);
    }

    private boolean hayCaminoDeAltura(Nodo<T> nodo, int n) {
        if (n == 0) {
            return true;
        } else {
            if (nodo == null) {
                return false;
            } else {
                return hayCaminoDeAltura(nodo.izq, n - 1) || hayCaminoDeAltura(nodo.der, n - 1);
            }
        }
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
