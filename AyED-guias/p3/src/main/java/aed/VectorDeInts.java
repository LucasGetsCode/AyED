package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 0;

    private int[] arr;

    public VectorDeInts() {
        int[] res = new int[CAPACIDAD_INICIAL];
        this.arr = res;
    }

    public VectorDeInts(VectorDeInts vector) {
        int[] res = new int[vector.longitud()];
        for (int i = 0; i < vector.longitud(); i++) {
            res[i] = vector.arr[i];
        }
        this.arr = res;
    }

    public int longitud() {
        return this.arr.length;
    }

    public void agregarAtras(int e) {
        int[] res = new int[this.longitud() + 1];
        for (int i = 0; i < this.longitud(); i++) {
            res[i] = this.arr[i];
        }
        res[this.longitud()] = e;
        this.arr = res;
    }

    public int obtener(int i) {
        if (i < this.longitud()) {
            return this.arr[i];
        } else {
            return -1;
        }
    }

    public void quitarAtras() {
        int[] res = new int[this.longitud() - 1];
        for (int i = 0; i < this.longitud() - 1; i++) {
            res[i] = this.arr[i];
        }
        this.arr = res;
    }

    public void modificarPosicion(int indice, int valor) {
        if (indice < this.longitud()) {
            this.arr[indice] = valor;
        }
    }

    public VectorDeInts copiar() {
        VectorDeInts res = new VectorDeInts(this);
        return res;
    }

}
