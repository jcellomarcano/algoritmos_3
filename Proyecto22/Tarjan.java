import java.util.*;

/**
 * Created by J&J  on 11/11/2017.
 */
public class Tarjan {
    private int centinela;
    private List<Integer> pilaVertice;
    private int[] componentesConex;
    private int[] indice;
    private int[] menor;
    private boolean[] estaEnPila;
    private GrafoDirigido miGrafito;

    /**
     * Constructor para la clase de Tarjan
     * Aqui conseguiremos las componentes conexas del grafo
     */

    public Tarjan(GrafoDirigido grafo){
        this.centinela = 0;
        this.pilaVertice = new ArrayList<Integer>();
        this.componentesConex = new int[grafo.numeroDeVertices()];
        this.indice = new int[grafo.numeroDeVertices()];
        this.menor = new int[grafo.numeroDeVertices()];
        this.estaEnPila = new boolean[grafo.numeroDeVertices()];
        this.miGrafito = grafo;

        // Pasamos a aplicarles tarjan al Grafo, recorriendo todos sus nodos
        for (int nodo = 0; nodo < this.miGrafito.vertices().size(); nodo++) {
            if (this.indice[nodo] == 0) ;
            aplicaTarjan(miGrafito.vertices().get(nodo));
            }
    }

        /**
         * A partir de aqui empezamos a crear la funciona Tarjan que se encargara de
         * devolerme las componentes fuertemente conexas
         * @param nodo
         */

        public void aplicaTarjan(Vertice nodo){

            //creamos un iterador para buscar dentro de la profundidad de todos los sucesores de cada nodo

            ListIterator<Lado> sucesores = this.miGrafito.sucesores(Integer.toString(nodo)).iterator();
    }
}




