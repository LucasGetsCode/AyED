package aed;

public class Fecha {
    private Integer dia;
    private Integer mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    @Override
    public String toString() {
        String res = this.dia.toString() + "/" + this.mes.toString();
        return res;
    }

    @Override
    public boolean equals(Object otra) {
        boolean res;
        res = otra != null;
        res = res && otra.getClass() == this.getClass();

        if (res) {
            Fecha otraFecha = (Fecha) otra;

            res = this.dia == otraFecha.dia && this.mes == otraFecha.mes;
        }

        return res;
    }

    public void incrementarDia() {
        if (diasEnMes(this.mes) == this.dia) {
            if (this.mes == 12) {
                this.dia = 1;
                this.mes = 1;
            } else {
                this.mes = this.mes + 1;
                this.dia = 1;
            }
        } else {
            this.dia = this.dia + 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
