package aed;

public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        for (int i = 0; i < fragments.length; i++) {
            int j = i;
            while (j != 0 && fragments[j].compareTo(fragments[j - 1]) < 0) {
                Fragment fragmento = fragments[j];
                fragments[j] = fragments[j - 1];
                fragments[j - 1] = fragmento;
                j--;
            }
        }
        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) { // O(k*log n)
        int cantSuperanUmbral = 0;
        for (int i = 0; i < routers.length; i++) { // O(n)
            if (routers[i].getTrafico() > umbral) {
                cantSuperanUmbral++;
            }
        }
        HeapArray<Router> heap = new HeapArray<Router>(routers); // O(n)
        int tope = minimo(minimo(routers.length, cantSuperanUmbral), k);
        Router[] res = new Router[tope]; // O(k)
        int i = 0;
        while (i < tope && heap.proximo().getTrafico() > umbral) { // k*O(log n) = O(klog n)
            res[i] = heap.desencolar(); // O(log n)
            i++;
        }
        return res;
    }

    private int minimo(int x, int y) {
        return x > y ? y : x;
    }

    @SuppressWarnings("unchecked")
    public IPv4Address[] sortIPv4(String[] ipv4) {
        ListaEnlazada<IPv4Address>[][] bucketDebuckets = new ListaEnlazada[4][256];
        IPv4Address[] ipv4adresses = new IPv4Address[ipv4.length];
        for (int i = 0; i < ipv4.length; i++) {
            IPv4Address ip = new IPv4Address(ipv4[i]);
            ipv4adresses[i] = ip;
        }
        for (int m = 0; m < 4; m++) {
            for (int i = 0; i < ipv4adresses.length; i++) {
                int octeto = ipv4adresses[i].getOctet(m);
                bucketDebuckets[m][octeto].agregarAtras(ipv4adresses[i]);
            }
        }
        return null;
    }

}
