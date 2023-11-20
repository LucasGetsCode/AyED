package ej7;

/**
 * Hello world!
 */
public final class HeapArbolBinario<T extends Comparable<T>> implements HeapInterface<T> {
    private Nodo<T> raiz;
    private int longitud;

    public HeapArbolBinario() {
        raiz = null;
        longitud = 1;
    }

    public HeapArbolBinario(T[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            encolar(arreglo[i]);
        }
    }

    public void encolar(T e) {
        ;
        longitud++;
    }

    public T desencolar() {
        longitud--;
        return raiz.valor;
    }

    public T proximo() {
        return raiz.valor;
    }

    private int ultimaPosicion() {
        int i = 1;
        int power = 0;
        while (i < longitud + 1) {
            i = i * 2;
            power++;
        }
        return (int) (longitud - Math.pow(2, power));
    }

    private Nodo<T> posicionCorrespondiente(Nodo<T> nodo, boolean direccion) {
        return raiz;
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
