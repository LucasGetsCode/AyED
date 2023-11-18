package ej6;

import org.junit.jupiter.api.Test;

import ej6.ArbolBinario;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class ArbolBinarioTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        assertEquals(1, 1);
    }

    @Test
    void nivel4Completo() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[15];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(true, arbol.nivelCompleto(1));
        assertEquals(true, arbol.nivelCompleto(2));
        assertEquals(true, arbol.nivelCompleto(3));
        assertEquals(true, arbol.nivelCompleto(4));
        assertEquals(false, arbol.nivelCompleto(5));
        assertEquals(true, arbol.estaCompleto());
    }

    @Test
    void nivel4Incompleto() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[13];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(true, arbol.nivelCompleto(1));
        assertEquals(true, arbol.nivelCompleto(2));
        assertEquals(true, arbol.nivelCompleto(3));
        assertEquals(false, arbol.nivelCompleto(4));
        assertEquals(false, arbol.nivelCompleto(5));
        assertEquals(false, arbol.estaCompleto());
    }

    @Test
    void nivel1Incompleto() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[0];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(false, arbol.nivelCompleto(1));
        assertEquals(false, arbol.estaCompleto());
    }

    @Test
    void nivel1Completo() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[1];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(true, arbol.nivelCompleto(1));
        assertEquals(true, arbol.estaCompleto());
    }

    @Test
    void nivel2Incompleto() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[2];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(true, arbol.nivelCompleto(1));
        assertEquals(false, arbol.nivelCompleto(2));
        assertEquals(false, arbol.estaCompleto());
    }

    @Test
    void nivel10Completo() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[1023];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(true, arbol.nivelCompleto(1));
        assertEquals(true, arbol.nivelCompleto(2));
        assertEquals(true, arbol.nivelCompleto(3));
        assertEquals(true, arbol.nivelCompleto(4));
        assertEquals(true, arbol.nivelCompleto(5));
        assertEquals(true, arbol.nivelCompleto(6));
        assertEquals(true, arbol.nivelCompleto(7));
        assertEquals(true, arbol.nivelCompleto(8));
        assertEquals(true, arbol.nivelCompleto(9));
        assertEquals(true, arbol.nivelCompleto(10));
        assertEquals(false, arbol.nivelCompleto(11));
        assertEquals(true, arbol.estaCompleto());
    }

    @Test
    void nivel10Incompleto() {
        Nodo<Integer>[] nodos = (Nodo<Integer>[]) new Nodo[965];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo<Integer>(i * i % 10);
        }
        ArbolBinario<Integer> arbol = new ArbolBinario<Integer>(nodos);

        assertEquals(true, arbol.nivelCompleto(1));
        assertEquals(true, arbol.nivelCompleto(2));
        assertEquals(true, arbol.nivelCompleto(3));
        assertEquals(true, arbol.nivelCompleto(4));
        assertEquals(true, arbol.nivelCompleto(5));
        assertEquals(true, arbol.nivelCompleto(6));
        assertEquals(true, arbol.nivelCompleto(7));
        assertEquals(true, arbol.nivelCompleto(8));
        assertEquals(true, arbol.nivelCompleto(9));
        assertEquals(false, arbol.nivelCompleto(10));
        assertEquals(false, arbol.nivelCompleto(11));
        assertEquals(false, arbol.estaCompleto());
    }
}
