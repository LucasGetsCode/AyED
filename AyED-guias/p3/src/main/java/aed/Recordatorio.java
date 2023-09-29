package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.horario = horario;
    }

    public Horario horario() {
        return new Horario(this.horario.hora(), this.horario.minutos());
    }

    public Fecha fecha() {
        return new Fecha(this.fecha.dia(), this.fecha.mes());
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return mensaje + " @ " + fecha.toString() + " " + horario.toString();
    }

}
