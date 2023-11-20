package ej7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        assertEquals(1, 1);
    }

    @Test
    void prueba1() {
        Integer[] arreglo = { 3, 1, 8, 2, 12, 63, 84, 2, 55, 42 };
        Integer[] arregloOrdenado = { 84, 63, 55, 42, 12, 8, 3, 2, 2, 1 };
        HeapArray<Integer> heapArray = new HeapArray<Integer>(arreglo);
        for (int i = 0; i < arregloOrdenado.length; i++) {
            assertEquals(arregloOrdenado[i], heapArray.desencolar());
        }
    }
}
