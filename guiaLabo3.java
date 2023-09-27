// 1. Vector De Ints

public class VectorDeInts {
    private Array<Int> arr;

    public VectorDeInts() {
        Array<Int> res = new Array<Int>(0);
        return res;
    }

    public int longitud() {
        return len(arr);
    }

    public void agregarAtras(Int e) {
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

    public void modificarPosicion(int e, int i) {
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

VectorDeInts hola = new VectorDeInts();