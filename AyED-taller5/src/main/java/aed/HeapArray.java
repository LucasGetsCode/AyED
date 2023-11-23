package aed;

@SuppressWarnings("unchecked")
public class HeapArray<T extends Comparable<T>> implements HeapInterface<T> { // Max Heap.
    T[] elems; // Para complejidades: n = longitud. Se toma el peor caso.
    int longitud; // Si no se aclara se asume que es O(1).

    // public static void main(String[] args) {
    // Integer[] arr = { 5, 2, 3, 1, 8, 2, 4, 10, 2, 7, 42, 73, 71, 13, 23, 53, 92,
    // 10, -20 };
    // ColaDePrioridad<Integer> cola = new ColaDePrioridad<Integer>(arr);
    // cola.print();
    // int max = cola.desencolar();
    // cola.print();
    // cola.encolar(max);
    // cola.print();
    // }

    public HeapArray(int longitud) { // O(n)
        this.elems = (T[]) new Comparable[longitud]; // O(n)
        this.longitud = longitud;
    }

    public HeapArray(T[] arreglo) { // O(n)
        this.longitud = arreglo.length;
        T[] arr = (T[]) new Comparable[longitud]; // O(n)
        for (int i = 0; i < longitud; i++) { // O(n)
            arr[i] = arreglo[i];
        }
        for (int i = longitud - 1; i >= 0; i--) { // O(n) por lo visto en clase del algoritmo de Floyd
            heapify(arr, i, longitud); // O(log(n) - altura(i))
        }
        this.elems = arr;
    }

    // requiere que longitud > 0
    @Override
    public T desencolar() { // O(log n)
        T maximo = elems[0];
        T ultimo = elems[longitud - 1];
        elems[0] = ultimo;
        // elems[longitud - 1] = -1; // si se reduce la longitud no hace falta
        // reemplazar el elemento
        longitud--;
        heapify(elems, 0, longitud); // O(log n)

        return maximo;
    }

    @Override
    public T proximo() { // O(1)
        return elems[0];
    }

    // requiere que longitud < elems.length
    @Override
    public void encolar(T e) { // O(log n)
        if (longitud == elems.length) { // O(n), pero solo pasa cada por lo menos n inserciones, amortizado es O(1).
            T[] new_elems = (T[]) new Comparable[longitud * 2];
            for (int k = 0; k < longitud; k++) { // O(n)
                new_elems[k] = elems[k];
            }
            elems = new_elems;
        }
        int i = longitud;
        elems[longitud] = e;
        longitud++;
        while (elems[i].compareTo(elems[(i - 1) / 2]) > 0) { // O(log n), cada vez se reduce a la mitad
            elems[i] = elems[(i - 1) / 2];
            elems[(i - 1) / 2] = e;
            i = (i - 1) / 2;
        }
    }

    public void print() {
        String res = "[";
        for (int i = 0; i < elems.length - 1; i++) {
            res += elems[i] + ", ";
        }
        res += elems[elems.length - 1] + "]";
        System.out.println(res);

        int max_long = 1;
        int altura = 1;
        while (2 * max_long < longitud) {
            max_long = 2 * max_long;
            altura++;
        }
        String linea = "";
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                for (int espacio = 0; espacio < max_long / (i + 1.2); espacio++) {
                    linea += "  ";
                }
                if ((int) Math.pow(2, i) + j - 1 < longitud) {
                    linea += elems[(int) Math.pow(2, i) + j - 1];
                }
            }
            System.out.println(linea);
            linea = "";
        }

    }

    // No sabemos calcular formalmente la complejidad de funciones recursivas, sin
    // embargo
    // va a haber a lo sumo (la altura del subárbol correspondiente a i)
    // recursiones, y
    // como solo incluye operaciones O(1) y su llamado recursivo, entonces podemos
    // decir
    // su cumplejidad es O(la altura del subárbol correspondiente a i) =
    // O(log(max_long) - nivel(i))
    private void heapify(T[] arr, int i, int max_long) { // O(log(max_long) - nivel(i))
        int hijoIzq = i * 2 + 1;
        int hijoDer = i * 2 + 2;
        boolean tieneHijoIzq = hijoIzq < max_long;
        boolean tieneHijoDer = hijoDer < max_long;
        boolean tieneHijos = tieneHijoDer || tieneHijoIzq;

        if (tieneHijos) {
            if ((tieneHijoIzq && arr[i].compareTo(arr[hijoIzq]) < 0)
                    || (tieneHijoDer && arr[i].compareTo(arr[hijoDer]) < 0)) {
                int mayorHijo;
                if (tieneHijoDer && arr[hijoDer].compareTo(arr[hijoIzq]) > 0) {
                    mayorHijo = hijoDer;
                } else {
                    mayorHijo = hijoIzq;
                }
                T mayorHijoValor = arr[mayorHijo];
                arr[mayorHijo] = arr[i];
                arr[i] = mayorHijoValor;
                heapify(arr, mayorHijo, max_long); // O(log(max_long) - nivel(i) - 1). Cada vez se va reduciendo u
            }
        }
    }
}
