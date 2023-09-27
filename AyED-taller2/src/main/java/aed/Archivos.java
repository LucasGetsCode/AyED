package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float[] ds = new float[largo];
        for (int i = 0; i < largo; i++) {
            ds[i] = entrada.nextFloat();
        }
        return ds;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] matriz = new float[filas][columnas];
        for (int i = 0; i < filas; i++) {
            matriz[i] = leerVector(entrada, columnas);
        }
        return matriz;
    }

    String agregarChars(String caracter, int veces) {
        String texto = "";
        for (int i = 0; i < veces; i++) {
            texto += caracter;
        }
        return texto;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        for (int i = 1; i <= alto; i++) {
            salida.print(agregarChars(" ", alto - i) + agregarChars("*", 2 * i - 1) + agregarChars(" ", alto - i));
            salida.print("\n");
        }
    }
}
