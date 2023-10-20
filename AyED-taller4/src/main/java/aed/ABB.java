package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo primero;
    private T maximo;
    private T minimo;
    int largo;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        primero = null;
        maximo = null;
        minimo = null;
    }

    public int cardinal() {
        return largo;
    }

    public T minimo(){          // requiere que exista un elemento
        return minimo;
    }

    public T maximo(){          // requiere que exista un elemento
        return maximo;
    }

    public void insertar(T elem){
        if (primero == null) {
            primero = Nodo(elem);
            maximo = elem;
            minimo = elem;
        } else {
            insertarRecursivo(primero, elem);
            if (maximo.valor.compareTo(elem) < 0) { // elem es mayor al valormáximo
                maximo = elem;
            }
            if (minimo.valor.compareTo(elem) > 0) { // elem es menor al valor mínimo
                minimo = elem;
            }
        }
    }

    public boolean pertenece(T elem){
        return perteneceRecursivo(primero, elem);
     }


    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }



    private insertarRecursivo(Nodo raiz, T elem) {
        if (raiz.valor.compareTo(elem) > 0) { // elem es menor al valor del nodo
            if (raiz.izq == null) {
                añadir(raiz, elem, false);
            } else {
                insertarRecursivo(raiz.izq, elem);
            }
        } else if (raiz.valor.compareTo(elem) < 0) { // elem es mayor al valor del nodo
            if (raiz.der == null) {
                añadir(raiz, elem, true);
            } else {
                insertarRecursivo(raiz.der, elem);
            }
        }
    }

    private añadir(Nodo padre, T elem, boolean dir) { // dir: false = izq, true = der
        Nodo hijo = Nodo(elem);
        if (dir == false) {
            padre.izq = hijo;
        } else {
            padre.der = hijo;
        }
        hijo.padre = padre;
    }

    private boolean perteneceRecursivo(Nodo raiz, T elem) {
        if (raiz == null) {
            return false;
        } else if (raiz.valor.compareTo(elem) == 0) { // son iguales
            return true;
        } else if (raiz.valor.compareTo(elem) > 0) { // elem es menor al valor del nodo
            return perteneceRecursivo(raiz.izq, elem);
        } else if (raiz.valor.compareTo(elem) < 0) { // elem es mayor al valor del nodo
            return perteneceRecursivo(raiz.der, elem);
        } else {
            return false;
        }
    }

}
