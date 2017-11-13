import java.util.*;

/**
 * Created by J&J  on 11/11/2017.
 */
public class Tarjan {
    private boolean[] estaEnPila;
    private int[] indice;
    private int[] menor;
    private int centinela;          // pre contador
    private Stack<Integer> pilaVertice;
    private int componentesConex;   // contador
    private GrafoDirigido miGrafito;

    /**
     * Constructor para la clase de Tarjan
     * Aqui conseguiremos las componentes conexas del grafo
     */


    public Tarjan(GrafoDirigido grafo){
        this.estaEnPila = new boolean[grafo.numeroDeVertices()];
        this.pilaVertice = new Stack<Integer>();
        this.indice = new int[grafo.numeroDeVertices()];
        this.menor = new int[grafo.numeroDeVertices()];
        this.miGrafito = grafo;

        // Pasamos a aplicarles tarjan al Grafo, recorriendo todos sus nodos
        for (Vertice iterador : this.miGrafito.vertices() ) {
            if (!this.estaEnPila[iterador.getIndice()]) ;
                creaTarjan(iterador);
            }
    }

    /**
         * A partir de aqui empezamos a crear la funciona Tarjan que se encargara de
         * devolerme las componentes fuertemente conexas
         * @param nodo
         */

        public void creaTarjan(Vertice nodo){
            this.estaEnPila[nodo.getIndice()] = true; // visitados
            this.menor[nodo.getIndice()] = this.centinela++;
            int min = this.menor[nodo.getIndice()];
            this.pilaVertice.push(nodo.getIndice());
            //creamos un iterador para buscar dentro de la profundidad de todos los sucesores de cada nodo
            for (Vertice w: this.miGrafito.adyacentes(nodo.getId())) {
                if(!estaEnPila[w.getIndice()]){
                    creaTarjan(w);
                }
                if (this.menor[w.getIndice()] < min){
                    min = this.menor[w.getIndice()];
                }

            }
            if (min == this.menor[nodo.getIndice()]) {
                this.menor[nodo.getIndice()] = min;
                return;
            }
                //Desempilamos los elementos que hemos metido en la pola
                //tales que perenencen en la misma componente hasta que lleguen al nodo que llama al metodo
                Vertice w;
                do {
                    w = this.miGrafito.obtenerVerticePorInx(this.pilaVertice.pop());
                    this.indice[w.getIndice()] = this.componentesConex;
                    this.menor[w.getIndice()] = this.miGrafito.numeroDeVertices();
                }while (w.getIndice() != nodo.getIndice());
               this.componentesConex++;
    }
    public int componenteConexa(){
        return this.componentesConex;
    }

    public boolean fuerteConexa(Vertice v, Vertice w){
        validarVertice(v);
        validarVertice(w);
        return this.indice[v.getIndice()] == this.indice[w.getIndice()];
    }
    public int id(Vertice v){
        validarVertice(v);
        return this.indice[v.getIndice()];
    }
    private void validarVertice(Vertice v){
        int V = this.estaEnPila.length;
        if(v.getIndice() < 0 || v.getIndice() >= V)
            throw new IllegalArgumentException("vertice"  + v + "no se encuentra entre 0 and" + (V-1));
    }

}
    /*
    Retorna el numero las compoentes conexas
     */






