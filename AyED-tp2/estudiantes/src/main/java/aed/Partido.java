package aed;

public class Partido implements Comparable<Partido> {
    public int votos;
    public String nombre;
    public int id;

    public Partido(int votos, String nombre, int id) {
        this.votos = votos;
        this.nombre = nombre;
        this.id = id;
    }

    public Partido(Partido partido) {
        this.votos = partido.votos;
        this.nombre = partido.nombre;
        this.id = partido.id;
    }

    public Partido() {
    }

    public int compareTo(Partido otro) {
        return this.votos - otro.votos;
    }

    public String toString() {
        return nombre + ": " + votos + ". Id: " + id;
    }
}