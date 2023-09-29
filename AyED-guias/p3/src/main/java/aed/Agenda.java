package aed;

import java.util.Vector;

public class Agenda {
    private Fecha fecha;
    private Vector<Recordatorio> recordatorios;

    public Agenda(Fecha fechaActual) {
        this.fecha = new Fecha(fechaActual.dia(), fechaActual.mes());
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.add(recordatorio);
    }

    @Override
    public String toString() {
        String res;
        res = this.fecha.toString() + "\n";
        res += "=====\n";
        for (int i = 0; i < recordatorios.size(); i++) {
            res += recordatorios.elementAt(i).toString() + "\n";
        }
        return res;
    }

    public void incrementarDia() {
        throw new UnsupportedOperationException("No implementada aun");

    }

    public Fecha fechaActual() {
        return new Fecha(this.fecha.dia(), this.fecha.mes());
    }

}
