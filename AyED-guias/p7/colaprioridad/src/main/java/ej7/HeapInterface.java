package ej7;

public interface HeapInterface<T extends Comparable<T>> {

    // requiere que longitud > 0
    T desencolar();

    T proximo();

    // requiere que longitud < elems.length
    void encolar(T e);

}