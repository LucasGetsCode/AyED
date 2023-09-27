package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoberturaTests {
    Cobertura cobertura = new Cobertura();

    @Test
    void testFizzBuzz() {
        assertEquals("FizzBuzz", cobertura.fizzBuzz(15));
        assertEquals("Fizz", cobertura.fizzBuzz(18));
        assertEquals("Buzz", cobertura.fizzBuzz(50));
        assertEquals("2", cobertura.fizzBuzz(2));
    }

    @Test
    void testNumeroCombinatorio() {
        assertTrue(cobertura.numeroCombinatorio(0, 0) == 1);
        assertTrue(cobertura.numeroCombinatorio(3, 3) == 1);
        assertTrue(cobertura.numeroCombinatorio(2, 3) == 0);
        assertTrue(cobertura.numeroCombinatorio(5, 2) == 10);
        assertTrue(cobertura.numeroCombinatorio(10, 5) == 252);
    }

    @Test
    void testRepeticionesConsecutivas() {
        assertEquals(cobertura.repeticionesConsecutivas(new int[] { 1 }), 0);
        assertEquals(cobertura.repeticionesConsecutivas(new int[] { 1, 2, 2, 3, 4, 5 }), 2);
        assertEquals(cobertura.repeticionesConsecutivas(new int[] { 5, 5 }), 2);
        assertEquals(cobertura.repeticionesConsecutivas(new int[] { 1, 2, 3, 4, 5 }), 1);
        assertEquals(cobertura.repeticionesConsecutivas(new int[] {}), 0);
        assertEquals(cobertura.repeticionesConsecutivas(new int[] { 4, 7, 7, 7, 7 }), 4);
    }
}
