import java.util.*;

/**
 * Created by Jesus Marcano 12-10359
 * Jose Basanta 13-10125
 * Clase Tarjan, la cual se encarga de generar todas las componentes conexas posibles a solucion, para despues generar el espacio solucion de la que si  son validas
 * on 11/11/2017.
 */
public class Tarjan1 {
    GrafoDirigido grafo;
    boolean[] visitados;
    Stack<Vertice> pila;
    int tiempo;
    int[] menorEnlace;
    List<List<Vertice>> componentes;

    public List<List<Vertice>> cfc(GrafoDirigido grafo) {
        int n = grafo.numeroDeVertices();
        this.grafo = grafo;
        visitados = new boolean[n];
        pila = new Stack<>();
        tiempo = 0;
        menorEnlace = new int[n];
        componentes = new ArrayList<>();

        for (int u = 0; u < n; u++) {
            if (!visitados[u]) {
                dfs(grafo.obtenerVerticePorInx(u));
            }
        }
        return componentes;
    }
    public void dfs (Vertice u){
        menorEnlace[u.getIndice()] = tiempo++;
        visitados[u.getIndice()] = true;
        pila.add(u);
        boolean raizComponente = true;

        for (Vertice v: grafo.sucesores(u.getId())) {
            if (!visitados[v.getIndice()]){
                dfs(v);
            }
            if (menorEnlace[u.getIndice()] > menorEnlace[v.getIndice()]){
                menorEnlace[u.getIndice()] = menorEnlace[v.getIndice()];
                raizComponente = false;
            }

        }
        if (raizComponente){
            List<Vertice> componente = new ArrayList<>();
            while (true){
                Vertice x = pila.pop();
                if(!x.getFrontera()){
                    componente.add(x);
                }
                menorEnlace[x.getIndice()] = Integer.MAX_VALUE;
                if (x.equals(u)){
                    break;
                }
            }
            if (componente.size() > 0) {
                componentes.add(componente);
            }
        }
    }
}