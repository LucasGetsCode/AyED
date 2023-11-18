public class MatrizInfinita {
    private boolean complemento;
    private boolean[][] valores;
    private int longitud;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public MatrizInfinita() { // O(1)
        complemento = true;
        valores = new boolean[1][1];
        longitud = 1;
    }

    public void asignar(int f, int c, boolean v) { // O(max(f,c)^2)
        int max = f > c ? f : c;
        if (longitud < max) {
            boolean[][] nuevosValores = new boolean[max][max];
            int i = 0;
            while (i < longitud) {
                int j = 0;
                while (j < longitud) {
                    nuevosValores[i][j] = valores[i][j];
                    j++;
                }
                i++;
            }
            longitud = max + 1;
            valores = nuevosValores;
        }
        valores[f][c] = valor(v);
    }

    public boolean ver(int f, int c) { // O(1)
        if (longitud < f || longitud < c) {
            return !complemento;
        } else {
            return valor(valores[f][c]);
        }
    }

    public void complementar() { // O(1)
        complemento = !complemento;
    }

    public void intersecar(MatrizInfinita otraMatriz) { // O(maxLong^2)
        int minLong = longitud < otraMatriz.longitud ? longitud : otraMatriz.longitud;
        int maxLong = longitud > otraMatriz.longitud ? longitud : otraMatriz.longitud;
        boolean[][] nuevosValores = new boolean[minLong][minLong]; // minlonga, milonga

        for (int i = 0; i < maxLong; i++) { // O(maxLong^2)
            for (int j = 0; j < maxLong; j++) {
                boolean sonIguales = false;
                if (i < minLong && j < minLong) {
                    sonIguales = valores[i][j] == otraMatriz.valores[i][j];
                }
                if (sonIguales) {
                    nuevosValores[i][j] = valor(valores[i][j]);
                } else {
                    nuevosValores[i][j] = !complemento;
                }
            }
        }
    }

    private boolean valor(boolean v) {
        if (v == complemento) {
            return true;
        } else {
            return false;
        }
    }
}
