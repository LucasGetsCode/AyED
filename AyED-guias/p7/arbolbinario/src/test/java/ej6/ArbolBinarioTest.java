package ej6;

import org.junit.jupiter.api.Test;

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
    void nivelCompleto() {
        Nodo[] nodos = new Nodo[15];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new Nodo(i * i % 10);
        }
        ArbolBinario<Int> arbol = ArbolBinario(nodos);
    }
}
