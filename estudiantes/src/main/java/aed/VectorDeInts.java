package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;

    public VectorDeInts() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public VectorDeInts(VectorDeInts vector) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int longitud() {
        return len(arr);
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
