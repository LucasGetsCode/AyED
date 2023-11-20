package aed;

public class SistemaCNE {
    Partido[] partidos;
    Distrito[] distritos;
    int votos_totales;
    boolean ballotage;
    Partido primero;
    Partido segundo;

    public class Distrito {
        // Constantes
        private String nombre;
        private int cant_bancas;
        private int max;
        private int min;
        // Variables
        private int votos_totales;
        private int[] votos_partidos;
        // dHondt
        private boolean dHondt_calculado;
        private int[] resultado_dHondt;
        private ColaDePrioridadPartido dHondt;

    }

    public class VotosPartido {
        private int presidente;
        private int diputados;

        VotosPartido(int presidente, int diputados) {
            this.presidente = presidente;
            this.diputados = diputados;
        }

        public int votosPresidente() {
            return presidente;
        }

        public int votosDiputados() {
            return diputados;
        }
    }

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos,
            int[] ultimasMesasDistritos) { // O(D*P)
        this.partidos = new Partido[nombresPartidos.length]; // O(P)
        this.distritos = new Distrito[nombresDistritos.length]; // O(D)
        this.ballotage = true;// new ColaDePrioridadPartido(nombresPartidos.length); // O(P)
        this.votos_totales = 0;

        for (int dist = 0; dist < distritos.length; dist++) { // O(D*P) porque se ejecuta D veces un bloque de código
                                                              // O(P)
            Distrito distrito = new Distrito();
            distrito.cant_bancas = diputadosPorDistrito[dist];
            distrito.nombre = nombresDistritos[dist];
            distrito.max = ultimasMesasDistritos[dist] - 1; // no inclusivo
            distrito.min = (dist == 0) ? 0 : (distritos[dist - 1].max + 1);
            distrito.votos_partidos = new int[nombresPartidos.length]; // O(P)
            distrito.votos_totales = 0;
            distrito.dHondt_calculado = false;
            distritos[dist] = distrito;
        }

        for (int part = 0; part < partidos.length; part++) { // O(P)
            Partido partido = new Partido();
            partido.nombre = nombresPartidos[part];
            partido.votos = 0;
            partido.id = part;
            partidos[part] = partido;
        }
        primero = partidos[0];
        segundo = new Partido(0, "cero", -1);
    }

    public String nombrePartido(int idPartido) { // O(1)
        return partidos[idPartido].nombre;
    }

    public String nombreDistrito(int idDistrito) { // O(1)
        return distritos[idDistrito].nombre;
    }

    public int diputadosEnDisputa(int idDistrito) { // O(1)
        return distritos[idDistrito].cant_bancas;
    }

    public String distritoDeMesa(int idMesa) { // O(log(D))
        int indice = busquedaBinaria(distritos, idMesa); // O(log(D))
        return distritos[indice].nombre;
    }

    // O(P + log(D))
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) { // actaMesa.length = partidos.length
        int indice = busquedaBinaria(distritos, idMesa); // O(log(D))
        Distrito distrito = distritos[indice];
        for (int part = 0; part < actaMesa.length; part++) { // O(P)
            distrito.votos_partidos[part] += actaMesa[part].votosDiputados();
            distrito.votos_totales += actaMesa[part].votosDiputados();
            partidos[part].votos += actaMesa[part].votosPresidente();
            votos_totales += actaMesa[part].votosPresidente();
            if (part != partidos.length) {
                actualizarPrimeros(partidos[part]); // O(1)
            }
        }

        // ej 9
        distrito.dHondt_calculado = false;
        distrito.resultado_dHondt = new int[partidos.length - 1]; // O(P)
        int umbral = distrito.votos_totales * 3 / 100;
        distrito.dHondt = new ColaDePrioridadPartido(distrito.votos_partidos, umbral); // O(P)
    }

    public int votosPresidenciales(int idPartido) { // O(1)
        return partidos[idPartido].votos;
    }

    public int votosDiputados(int idPartido, int idDistrito) { // O(1)
        Distrito distrito = distritos[idDistrito];
        int partido = distrito.votos_partidos[idPartido];
        return partido;
    }

    public int[] resultadosDiputados(int idDistrito) { // O(Dd*log(P))
        Distrito distrito = distritos[idDistrito];
        int[] res = distrito.resultado_dHondt;
        if (!distrito.dHondt_calculado) {
            ColaDePrioridadPartido votos = distrito.dHondt;
            // O(Dd*log(P)) porque se realiza Dd veces un bloque de código O(log(P))
            for (int i = 0; i < distrito.cant_bancas; i++) { // O(Dd)
                Partido banca = votos.proximo(); // O(1)
                res[banca.id] += 1;
                banca.votos = distrito.votos_partidos[banca.id] / (res[banca.id] + 1);
                votos.reordenar(); // O(log(P))
            }
            distrito.dHondt_calculado = true;
        }
        return res;
    }

    public boolean hayBallotage() {
        boolean res = true;
        if (porcentaje(primero, votos_totales) >= 45) {
            res = false;
        } else if (porcentaje(primero, votos_totales) >= 40
                && porcentaje(primero, votos_totales) - porcentaje(segundo, votos_totales) >= 10) {
            res = false;
        }
        return res;
    }

    // requiere una lista ordenada y que el elemento se encuentre en la lista.
    private int busquedaBinaria(Distrito[] arr, int valor) { // O(log(|arr|))
        int inicio = 0;
        int fin = arr.length - 1;
        if (arr.length == 0) {
            return -1;
        }
        if (arr[inicio].max > valor) {
            return inicio;
        } else if (arr[fin].min <= valor) {
            return fin;
        } else {
            while (inicio + 1 < fin) {
                int medio = (inicio + fin) / 2;
                if (arr[medio].min <= valor) {
                    inicio = medio;
                } else {
                    fin = medio;
                }
            }
            return inicio;
        }
    }

    private int porcentaje(Partido partido, int total) { // O(1)
        float votos = partido.votos;
        return (int) ((float) votos * 100 / total);
    }

    private void actualizarPrimeros(Partido partido) { // O(1)
        if (partido.votos >= segundo.votos && partido.id != primero.id) {
            if (partido.votos >= primero.votos) {
                segundo = primero;
                primero = partido;
            } else {
                segundo = partido;
            }
        }
    }

}
