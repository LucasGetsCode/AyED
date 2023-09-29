package aed;

public class Horario {
    private Integer hora;
    private Integer minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        return this.hora.toString() + ":" + this.minutos.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean res;
        res = otro != null;
        res = res && otro.getClass() == this.getClass();

        if (res) {
            Horario otroHorario = (Horario) otro;

            res = this.hora == otroHorario.hora && this.minutos == otroHorario.minutos;
        }
        return res;
    }

}
