package aed;

//import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo primero;
    private T maximo;
    private T minimo;
    private int largo;

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

    public T minimo() { // requiere que exista un elemento
        return minimo;
    }

    public T maximo() { // requiere que exista un elemento
        return maximo;
    }

    public void insertar(T elem) {
        if (primero == null) {
            primero = new Nodo(elem);
            maximo = elem;
            minimo = elem;
            largo = 1;
        } else {
            insertarRecursivo(primero, elem);
            if (maximo.compareTo(elem) < 0) { // elem es mayor al valormáximo
                maximo = elem;
            }
            if (minimo.compareTo(elem) > 0) { // elem es menor al valor mínimo
                minimo = elem;
            }
        }
    }

    public boolean pertenece(T elem) {
        Nodo objetivo = busquedaNodo(primero, elem);
        if (objetivo == null) {
            return false;
        } else {
            return true;
        }
        // return perteneceRecursivo(primero, elem);
    }

    public void eliminar(T elem) {
        Nodo objetivo = busquedaNodo(primero, elem);
        if (objetivo != null) { // el caso 0 es que no exista elem en el ABB

            boolean tienePadre = objetivo.padre != null;
            boolean esHijoIzq = false; // dir: false = izq, true = der
            boolean esHijoDer = false;
            boolean tieneHijoIzq = objetivo.izq != null;
            boolean tieneHijoDer = objetivo.der != null;
            if (tienePadre) {
                esHijoIzq = tienePadre && (objetivo.padre.izq == objetivo);
                esHijoDer = !esHijoIzq;
            }

            if (!tieneHijoIzq && !tieneHijoDer) { // no tiene hijos, caso 1
                if (!tienePadre) { // objetivo es el primero y único elemento
                    primero = null;
                } else { // objetivo no es el primero
                    if (esHijoIzq) {
                        objetivo.padre.izq = null;
                        if (objetivo.valor == minimo) {
                            minimo = sucesor(objetivo, elem).valor;
                        }
                    } else {
                        objetivo.padre.der = null;
                        if (objetivo.valor == maximo) {
                            maximo = antecesor(objetivo, elem).valor;
                        }
                    }
                }

            } else if (tieneHijoIzq != tieneHijoDer) { // tiene un único hijo, caso 2
                if (!tienePadre) { // objetivo es el primero y tiene un hijo
                    if (tieneHijoIzq) {
                        primero = objetivo.izq;
                        maximo = antecesor(objetivo, elem).valor; // el nuevo máximo es el segundo más grande
                    } else {
                        primero = objetivo.der;
                        minimo = sucesor(objetivo, elem).valor; // el nuevo mínimo es el segundo más chico
                    }
                } else { // objetivo tiene padre y un único hijo
                    Nodo siguienteNodo;
                    if (tieneHijoIzq) {
                        siguienteNodo = objetivo.izq;
                        if (objetivo.valor == maximo) { // si estoy eliminando el máximo y su hijo es menor a él, busco
                                                        // el nuevo máximo
                            maximo = antecesor(objetivo, elem).valor;
                        }
                    } else {
                        siguienteNodo = objetivo.der;
                        if (objetivo.valor == minimo) { // si estoy eliminando el mínimo y su hijo es mayor a él, busco
                                                        // el nuevo mínimo
                            minimo = sucesor(objetivo, elem).valor;
                        }
                    }
                    if (esHijoIzq) {
                        objetivo.padre.izq = siguienteNodo;
                    } else {
                        objetivo.padre.der = siguienteNodo;
                    }
                }

            } else if (tieneHijoIzq && tieneHijoDer) { // tiene 2 hijos, caso 3
                Nodo sucesor = sucesor(objetivo, elem);
                T valor = sucesor.valor;
                eliminar(valor);
                objetivo.valor = valor;
            }
        }
    }

    public String toString() {
        return "{" + toStringRecursivo(primero) + "}";
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

    private void insertarRecursivo(Nodo raiz, T elem) {
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

    private void añadir(Nodo padre, T elem, boolean dir) { // dir: false = izq, true = der
        Nodo hijo = new Nodo(elem);
        if (dir == false) {
            padre.izq = hijo;
        } else {
            padre.der = hijo;
        }
        hijo.padre = padre;
        largo++;
    }

    /*
     * private boolean perteneceRecursivo(Nodo raiz, T elem) {
     * if (raiz == null) {
     * return false;
     * } else if (raiz.valor.compareTo(elem) > 0) { // elem es menor al valor del
     * nodo
     * return perteneceRecursivo(raiz.izq, elem);
     * } else if (raiz.valor.compareTo(elem) < 0) { // elem es mayor al valor del
     * nodo
     * return perteneceRecursivo(raiz.der, elem);
     * } else { // son iguales
     * return true;
     * }
     * }
     */

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

    private Nodo sucesor(Nodo raiz, T elem) { // el siguiente más grande
        if (raiz.der != null) { // tiene rama derecha
            Nodo actual = raiz.der;
            while (actual.izq != null) {
                actual = actual.izq;
            }
            return actual;
        } else { // no tiene rama derecha
            Nodo padre = raiz.padre;
            Nodo actual = raiz;
            while (padre != null && actual == padre.der) {
                actual = padre;
                padre = padre.padre;
            }
            return padre;
        }
    }

    private Nodo antecesor(Nodo raiz, T elem) { // el siguiente más chico
        if (raiz.izq != null) { // tiene rama derecha
            Nodo actual = raiz.izq;
            while (actual.der != null) {
                actual = actual.der;
            }
            return actual;
        } else { // no tiene rama derecha
            Nodo padre = raiz.padre;
            Nodo actual = raiz;
            while (padre != null && actual == padre.izq) {
                actual = padre;
                padre = padre.padre;
            }
            return padre;
        }
    }

    private String toStringRecursivo(Nodo actual) {
        if (actual == null) {
            return "";
        } else if (actual.valor != maximo) {
            return toStringRecursivo(actual.izq) + actual.valor + "," + toStringRecursivo(actual.der);
        } else {
            return toStringRecursivo(actual.izq) + actual.valor;
        }
    }
}
