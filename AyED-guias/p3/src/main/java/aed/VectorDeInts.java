package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;
    private Array<Int> arr;

    public VectorDeInts() {
        Array<Int> res = new Array<Int>(0);
        return res;
    }

    public VectorDeInts(VectorDeInts vector) {
        Array<Int> res = new Array<Int>(vector.longitud());
        for (int i = 0; i < vector.longitud(); i++) {
            res[i] = vector.arr[i];
        }
        return res;
    }

    public int longitud() {
        return len(this.arr);
    }

    public void agregarAtras(int e) {
        Array<Int> res = new Array<Int>(this.longitud() + 1);
        for (int i = 0; i < this.longitud(); i++) {
            res[i] = this.arr[i];
        }
        res[this.longitud()] = e;
        return res;
    }

    public int obtener(int i) {
        if (i < this.longitud()) {
            return this.arr[i];
        } else {
            return -1;
        }
    }

    public void quitarAtras() {
        Array<Int> res = new Array<Int>(this.longitud() - 1);
        for (int i = 0; i < this.longitud() - 1; i++) {
            res[i] = this.arr[i];
        }
        this.arr = res;
    }

    public void modificarPosicion(int indice, int valor) {
        if (i < this.longitud()) {
            this.arr[i] = e;
        } else {
            raise exception;
        }
    }

    public VectorDeInts copiar() {
        Array<Int> res = new Array<Int>(this.longitud());
        for (int i = 0; i < this.longitud(); i++) {
            res[i] = this.arr[i];
        }
        return res;
    }

}
