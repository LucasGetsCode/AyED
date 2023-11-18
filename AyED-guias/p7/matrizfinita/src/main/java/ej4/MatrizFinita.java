package ej4;

/**
 * Hello world!
 */
public final class MatrizFinita {
    private Nodo primero;
    private Nodo ultimo;
    private int filas;
    private int columnas;

    private class Nodo {
        public int valor;
        public int fila;
        public int col;
        public Nodo sig;
        public Nodo ant;

        public Nodo(int valor, int fila, int col) {
            this.valor = valor;
            this.fila = fila;
            this.col = col;
            this.sig = null;
            this.ant = null;
        }

        public Nodo(Nodo nodo) {
            this.valor = nodo.valor;
            this.fila = nodo.fila;
            this.col = nodo.col;
            this.sig = null;
            this.ant = null;
        }
    }

    public MatrizFinita(int filas, int columnas) {
        this.primero = null;
        this.ultimo = null;
        this.columnas = columnas;
        this.filas = filas;
    }

    // requiere que f y c estén en rango
    public void definir(int f, int c, int v) {
        if (primero == null) {
            primero = new Nodo(v, f, c);
            ultimo = primero;
        } else {
            Nodo actual = primero;
            boolean listo = false;
            while (actual != null && !listo) {
                if ((actual.fila == f && actual.col > c) || (actual.fila > f)) { // actual está después de (f,c)
                    Nodo nuevo = new Nodo(v, f, c);
                    nuevo.sig = actual;
                    if (actual.ant != null) {
                        actual.ant.sig = nuevo;
                    }
                    nuevo.ant = actual.ant;
                    actual.ant = nuevo;
                    if (actual == primero) {
                        primero = nuevo;
                    }
                    listo = true;
                } else if (actual.fila == f && actual.col == c) { // actual es (f,c)
                    actual.valor = v;
                    listo = true;
                } else if (actual.sig == null) { // actual es el último
                    Nodo nuevo = new Nodo(v, f, c);
                    actual.sig = nuevo;
                    nuevo.ant = actual;
                    ultimo = nuevo;
                    listo = true;
                } else { // actual está antes de (f,c) y no es el último
                    actual = actual.sig;
                }
            }
        }
    }

    public int cantFilas() {
        return filas;
    }

    public int cantColumnas() {
        return columnas;
    }

    public int obtener(int f, int c) {
        if (primero == null) {
            return 0;
        } else {
            Nodo actual = primero;
            while (actual != null) {
                if ((actual.fila == f && actual.col > c) || (actual.fila > f)) { // actual está después de (f,c)
                    return 0;
                } else if (actual.fila == f && actual.col == c) { // actual es (f,c)
                    return actual.valor;
                } else { // actual está antes de (f,c) y no es el último
                    actual = actual.sig;
                }
            }
            return 0;
        }
    }

    public void sumarMatrices(MatrizFinita otraMatriz) {
        Nodo actual1 = primero;
        Nodo actual2 = otraMatriz.primero;
        if (actual1 == null) {
            if (actual2 != null) {
                actual1 = new Nodo(actual2);
                ultimo = actual1;
                actual2 = actual2.sig;
            }
        }
        while (actual1 != null && actual2 != null) {
            if (posicion(actual1) > posicion(actual2)) { // actual1 está en una posición mayor
                Nodo nuevo = new Nodo(actual2);
                nuevo.ant = actual1.ant;
                nuevo.sig = actual1;
                if (actual1.ant != null) {
                    actual1.ant.sig = nuevo;
                } else {
                    primero = nuevo;
                }
                actual1.ant = nuevo;
                actual2 = actual2.sig;
            } else if (posicion(actual2) > posicion(actual1)) { // actual2 está en una posición mayor
                if (actual1.sig == null) {
                    Nodo nuevo = new Nodo(actual2);
                    nuevo.ant = actual1;
                    actual1.sig = nuevo;
                    ultimo = nuevo;
                    actual2 = actual2.sig;
                } else {
                    actual1 = actual1.sig;
                }
            } else { // son iguales las posiciones
                actual1.valor += actual2.valor;
                actual2 = actual2.sig;
            }
        }
    }

    public void print() {
        Nodo actual = primero;
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int numero = 0;
                if (actual != null) {
                    if (actual.fila == f && actual.col == c) {
                        numero = actual.valor;
                        actual = actual.sig;
                    }
                }
                System.out.println(numero + " ");
            }
            System.out.println("\n");
        }
    }

    private int posicion(int f, int c) {
        return columnas * f + c;
    }

    private int posicion(Nodo nodo) {
        if (nodo != null) {
            return columnas * nodo.fila + nodo.col;
        } else {
            return columnas * filas + 1;
        }
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        MatrizFinita matriz = new MatrizFinita(5, 5);
        matriz.definir(0, 0, 3);
        System.out.println("hola");
        matriz.definir(2, 1, 4);
        System.out.println("hola");
        matriz.definir(0, 0, 10);
        matriz.definir(4, 2, 2);
        matriz.definir(2, 3, -2);
        matriz.definir(3, 0, 1);
        System.out.println("hola");
        matriz.print();
        System.out.println("hola");
    }
}
