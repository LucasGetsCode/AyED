package aed;

public class SistemaCNE {
    Partido[] partidos;
    Distrito[] distritos;
    int votos_totales;
    ColaDePrioridadPartido[] dHondt;
    ColaDePrioridadPartido ballotage;

    public class Partido implements Comparable<Partido> {
        private int votos;
        private String nombre;
        private int id;

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

    public class Distrito {
        // Constantes
        private String nombre;
        private int cant_bancas;
        private int max;
        private int min;
        // Variables
        private int votos_totales;
        private Partido[] votos_partidos;
        private int[] resultado_dHondt;
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
        this.dHondt = new ColaDePrioridadPartido[nombresDistritos.length]; // O(D)
        this.ballotage = new ColaDePrioridadPartido(nombresPartidos.length); // O(P)
        this.votos_totales = 0;

        for (int dist = 0; dist < distritos.length; dist++) { // O(D*P) porque se ejecuta D veces un bloque de código
                                                              // O(P)
            Distrito distrito = new Distrito();
            distrito.cant_bancas = diputadosPorDistrito[dist];
            distrito.nombre = nombresDistritos[dist];
            distrito.max = ultimasMesasDistritos[dist] - 1; // no inclusivo
            distrito.min = (dist == 0) ? 0 : (distritos[dist - 1].max + 1);
            distrito.votos_partidos = new Partido[nombresPartidos.length]; // O(P)
            distrito.resultado_dHondt = new int[nombresPartidos.length - 1]; // O(P)
            for (int part = 0; part < nombresPartidos.length; part++) { // O(P).
                distrito.votos_partidos[part] = new Partido();
                distrito.votos_partidos[part].id = part;
                distrito.votos_partidos[part].nombre = nombresPartidos[part];
                distrito.votos_partidos[part].votos = 0; // En java no es necesario, se inicializan en 0,
                if (part != nombresPartidos.length - 1) {// pero para hacerlo más declarativo
                    distrito.resultado_dHondt[part] = 0;
                }
            }
            distrito.votos_totales = 0;
            distritos[dist] = distrito;

            dHondt[dist] = new ColaDePrioridadPartido(nombresPartidos.length - 1); // O(P)
        }

        for (int part = 0; part < partidos.length; part++) { // O(P)
            Partido partido = new Partido();
            partido.nombre = nombresPartidos[part];
            partido.votos = 0;
            partido.id = part;
            partidos[part] = partido;
        }
    }

    public String nombrePartido(int idPartido) {
        return partidos[idPartido].nombre;
    }

    public String nombreDistrito(int idDistrito) {
        return distritos[idDistrito].nombre;
    }

    public int diputadosEnDisputa(int idDistrito) { // O(1)
        return distritos[idDistrito].cant_bancas;
    }

    public String distritoDeMesa(int idMesa) {
        int indice = busquedaBinaria(distritos, idMesa);
        return distritos[indice].nombre;
    }

    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) { // actaMesa.length = partidos.length
        int indice = busquedaBinaria(distritos, idMesa);
        Distrito distrito = distritos[indice];
        for (int part = 0; part < actaMesa.length; part++) {
            distrito.votos_partidos[part].votos += actaMesa[part].votosDiputados();
            distrito.votos_totales += actaMesa[part].votosDiputados();
            if (part != actaMesa.length - 1) {
                distrito.resultado_dHondt[part] = 0;
            }
            partidos[part].votos += actaMesa[part].votosPresidente();
            votos_totales += actaMesa[part].votosPresidente();
        }
        this.ballotage = new ColaDePrioridadPartido(partidos);
        Partido[] dhondt_candidatos = new Partido[partidos.length - 1];
        for (int partido = 0; partido < partidos.length - 1; partido++) {
            if (porcentaje(distrito.votos_partidos[partido], distrito.votos_totales) >= 3) {
                dhondt_candidatos[partido] = distrito.votos_partidos[partido];
            } else {
                dhondt_candidatos[partido] = new Partido();
            }
        }
        dHondt[indice] = new ColaDePrioridadPartido(dhondt_candidatos);
    }

    public int votosPresidenciales(int idPartido) {
        return partidos[idPartido].votos;
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        Distrito distrito = distritos[idDistrito];
        Partido partido = distrito.votos_partidos[idPartido];
        return partido.votos;
    }

    public int[] resultadosDiputados(int idDistrito) { // O(Dd*log(P))
        ColaDePrioridadPartido votos = dHondt[idDistrito];
        int[] res = distritos[idDistrito].resultado_dHondt;
        // int[] res = new int[distritos[idDistrito].votos_partidos.length]; // O(P) ???
        int cant_bancas = distritos[idDistrito].cant_bancas;
        int[] devolver = new int[cant_bancas];
        for (int i = 0; i < cant_bancas; i++) { // O(Dd*log(P)) porque se realiza Dd veces un
                                                // bloque de código O(log(P))
            Partido banca = votos.desencolar(); // O(log(P))
            res[banca.id] += 1;
            System.out.println(distritos[idDistrito].votos_partidos[banca.id].toString());
            System.out.println(banca.toString());
            banca.votos = distritos[idDistrito].votos_partidos[banca.id].votos / (res[banca.id] + 1);
            // float divisor = (float) 1 / res[banca.id];
            // banca.votos = (int) (banca.votos / (1 + divisor));
            System.out.println(banca.toString());
            votos.encolar(banca); // O(log(P))
            devolver[i] = banca.id;
        }
        return res;
    }

    public boolean hayBallotage() {
        int primero = ballotage.proximo().votos;
        int segundo = ballotage.segundo().votos;
        int ptaje_primero = primero / votos_totales * 100;
        int ptaje_segundo = segundo / votos_totales * 100;
        if (ptaje_primero >= 45) {
            return false;
        } else if (ptaje_primero >= 40 && (ptaje_primero - ptaje_segundo) >= 10) {
            return false;
        } else {
            return true;
        }
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

    private int porcentaje(Partido partido, int total) {
        float votos = partido.votos;
        return (int) (votos / total * 100);
    }

    public class ColaDePrioridadPartido { // Max Heap.
        Partido[] elems; // Para complejidades: n = longitud. Se toma el peor caso.
        int longitud; // Si no se aclara se asume que es O(1).

        public ColaDePrioridadPartido(int longitud) { // O(n)
            // this.elems = (Partido[]) new Comparable[longitud]; // O(n)
            this.elems = new Partido[longitud];
            this.longitud = longitud;
        }

        public ColaDePrioridadPartido(Partido[] arreglo) { // O(n)
            this.longitud = arreglo.length;
            Partido[] arr = new Partido[longitud]; // O(n)
            for (int i = 0; i < longitud; i++) { // O(n)
                arr[i] = new Partido(arreglo[i]);
            }
            for (int i = longitud - 1; i >= 0; i--) { // O(n) por lo visto en clase del algoritmo de Floyd
                heapify(arr, i, longitud); // O(log(n) - altura(i))
            }
            this.elems = arr;
        }

        // requiere que longitud > 0
        public Partido desencolar() { // O(log n)
            Partido maximo = elems[0];
            Partido ultimo = elems[longitud - 1];
            elems[0] = ultimo;
            // elems[longitud - 1] = -1; // si se reduce la longitud no hace falta
            // reemplazar el elemento
            longitud--;
            heapify(elems, 0, longitud); // O(log n)

            return maximo;
        }

        public Partido proximo() { // O(1)
            return elems[0];
        }

        // requiere longitud > 1
        public Partido segundo() { // O(1)
            if (longitud > 2) {
                if (elems[1].compareTo(elems[2]) > 0) {
                    return elems[1];
                } else {
                    return elems[2];
                }
            } else {
                return elems[1];
            }
        }

        // requiere que longitud < elems.length
        public void encolar(Partido e) { // O(log n)
            if (longitud == elems.length) { // O(n), pero solo pasa cada por lo menos n inserciones, amortizado es O(1).
                                            // Igualmente no vamos a utilizarlo en el tp, pero quería escribirlo para
                                            // que
                                            // quede completo
                Partido[] new_elems = new Partido[longitud * 2];
                for (int k = 0; k < longitud; k++) { // O(n)
                    new_elems[k] = elems[k];
                }
                elems = new_elems;
            }
            int i = longitud;
            elems[longitud] = e;
            longitud++;
            while (elems[i].compareTo(elems[(i - 1) / 2]) > 0) { // O(log n), cada vez se reduce a la mitad
                elems[i] = elems[(i - 1) / 2];
                elems[(i - 1) / 2] = e;
                i = (i - 1) / 2;
            }
        }

        public void print() {
            String res = "[";
            for (int i = 0; i < elems.length - 1; i++) {
                res += elems[i] + ", ";
            }
            res += elems[elems.length - 1] + "]";
            System.out.println(res);

            int max_long = 1;
            int altura = 1;
            while (2 * max_long < longitud) {
                max_long = 2 * max_long;
                altura++;
            }
            String linea = "";
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < Math.pow(2, i); j++) {
                    for (int espacio = 0; espacio < max_long / (i + 1.2); espacio++) {
                        linea += "  ";
                    }
                    if ((int) Math.pow(2, i) + j - 1 < longitud) {
                        linea += elems[(int) Math.pow(2, i) + j - 1];
                    }
                }
                System.out.println(linea);
                linea = "";
            }

        }

        // No sabemos calcular formalmente la complejidad de funciones recursivas, sin
        // embargo
        // va a haber a lo sumo (la altura del subárbol correspondiente a i)
        // recursiones, y
        // como solo incluye operaciones O(1) y su llamado recursivo, entonces podemos
        // decir
        // su cumplejidad es O(la altura del subárbol correspondiente a i) =
        // O(log(max_long) - nivel(i))
        private void heapify(Partido[] arr, int i, int max_long) { // O(log(max_long) - nivel(i))
            int hijoIzq = i * 2 + 1;
            int hijoDer = i * 2 + 2;
            boolean tieneHijoIzq = hijoIzq < max_long;
            boolean tieneHijoDer = hijoDer < max_long;
            boolean tieneHijos = tieneHijoDer || tieneHijoIzq;

            if (tieneHijos) {
                if ((tieneHijoIzq && arr[i].compareTo(arr[hijoIzq]) < 0)
                        || (tieneHijoDer && arr[i].compareTo(arr[hijoDer]) < 0)) {
                    int mayorHijo;
                    if (tieneHijoDer && arr[hijoDer].compareTo(arr[hijoIzq]) > 0) {
                        mayorHijo = hijoDer;
                    } else {
                        mayorHijo = hijoIzq;
                    }
                    Partido mayorHijoValor = arr[mayorHijo];
                    arr[mayorHijo] = arr[i];
                    arr[i] = mayorHijoValor;
                    heapify(arr, mayorHijo, max_long); // O(log(max_long) - nivel(i) - 1). Cada vez se va reduciendo u
                }
            }
        }
    }

}
