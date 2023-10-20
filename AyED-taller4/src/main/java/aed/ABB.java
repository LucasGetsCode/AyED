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
        Nodo objetivo = busquedaNodo(primero, elem);
        if (objetivo != null) {

            boolean tienePadre = objetivo.padre != null;
            boolean esHijoIzq;  // dir: false = izq, true = der
            boolean esHijoDer;
            if (tienePadre) {
                esHijoIzq = (objetivo.padre.izq == objetivo);
                esHijoDer = !esHijoIzq;
            }
            if (objetivo.izq == null && objetivo.der == null) { // no tiene hijos
                if (!(tienePadre)) { // objetivo es el primero y único elemento
                    primero = null;
                } else { // objetivo no es el primero
                    if (esHijoIzq) { // es el hijo izquierdo
                        objetivo.padre.izq = null;
                    } else { // es el hijo derecho
                        objetivo.padre.der = null;
                    }
                }
            } else if ((objetivo.izq != null) != (objetivo.der != null)) { // tiene un único hijo
                if (!(tienePadre)) { // objetivo es el primero y tiene hijos
                    if (objetivo.izq != null) {
                        primero = objetivo.izq;
                        maximo = anterior(objetivo, elem).valor; // el nuevo máximo es el segundo más grande
                    } else {
                        primero = objetivo.der;
                        minimo = sucesor(objetivo, elem).valor; // el nuevo mínimo es el segundo más chico
                    }
                } else { // objetivo tiene padre y un único hijo
                    if (objetivo.izq != null) {
                        objetivo.padre = objetivo.izq;
                        maximo = anterior(objetivo, elem).valor; // el nuevo máximo es el segundo más grande
                    } else {
                        primero = objetivo.der;
                        minimo = sucesor(objetivo, elem).valor; // el nuevo mínimo es el segundo más chico
                    }
                }
            }   

        }
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
        largo++;
    }

    private boolean perteneceRecursivo(Nodo raiz, T elem) {
        if (raiz == null) {
            return false;
        } else if (raiz.valor.compareTo(elem) > 0) { // elem es menor al valor del nodo
            return perteneceRecursivo(raiz.izq, elem);
        } else if (raiz.valor.compareTo(elem) < 0) { // elem es mayor al valor del nodo
            return perteneceRecursivo(raiz.der, elem);
        } else { // son iguales
            return true;
        }
    }

    private Nodo busquedaNodo(Nodo raiz, T elem) {
        if (raiz == null) {
            return null;
        } else if (raiz.valor.compareTo(elem) > 0) { // elem es menor al valor del nodo
            return busquedaNodo(raiz.izq, elem);
        } else if (raiz.valor.compareTo(elem) < 0) { // elem es mayor al valor del nodo
            return busquedaNodo(raiz.der, elem);
        } else { // son iguales
            return raiz;
        }
    }

    private Nodo sucesor(Nodo raiz, T elem) {
        return primero;
    }
}
