package aed;

class Funciones {
    int cuadrado(int x) {
        int res = x * x;
        return res;
    }
  
    double distancia(double x, double y) {
        double res = Math.sqrt(x*x+y*y);
        return res;
    }

    boolean esPar(int n) {
        boolean res = (n % 2) == 0;
        return res;
    }

    boolean multiploDe(int x, int y) {
        return (x % y) == 0;
    }

    boolean esBisiesto(int n) {
        boolean res = (multiploDe(n, 4) && !multiploDe(n, 100)) || multiploDe(n, 400);
        return res;
    }

    int factorialIterativo(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialIterativo(n-1);
    }

    boolean esPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int n:numeros) {
            res += n;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        for (int i=0; i < numeros.length; i++) {
            if (buscado == numeros[i]) {
                return i;
            }
        }
        return 0;
    }

    boolean tienePrimo(int[] numeros) {
        for (int n:numeros) {
            if (esPrimo(n)) {
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int n:numeros) {
            if (!esPar(n)) {
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        for (int i = 0;  i < s1.length(); i++) {
            if (i >= s2.length() || s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        for (int i = s1.length();  i > 0; i--) {
            if (i > s2.length() || s1.charAt(i-1) != s2.charAt(i-s1.length()+s2.length()-1)) {
                return false;
            }
        }
        return true;
    }
}
