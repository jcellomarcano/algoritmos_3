/**
 * Creado por
 * Jose Basanta 13-10125
 * Jesus Marcano 12-10359
 * Clase que se encarga de corroborar los espacion inundados dentro de la ciudad
 */

import java.util.LinkedList;
import java.util.List;

public class Charquitos {
    List<List<Vertice>> listaSoluciones;
    GrafoDirigido grafo;
    LinkedList<List<Vertice>> inundados;

    public Charquitos(List<List<Vertice>> listaSoluciones, GrafoDirigido grafo){
        this.listaSoluciones = listaSoluciones;
        this.grafo = grafo;
        this.inundados = new LinkedList<List<Vertice>>();
    }

    public List<List<Vertice>> getInundados() {
        
        externo:for (List<Vertice> iterador:listaSoluciones) {

            for (Vertice k: iterador) {
                List<Vertice> predecesores = grafo.predecesores(k.getId());

                for (Vertice pre : predecesores){

                    List<Vertice> sucesores = grafo.sucesores(pre.getId());

                    for (Vertice w: sucesores){

                        if (w.getFrontera()){
                            break;
                        }

                        else{
                            iterador.add(w);
                        }
                    }
                }

            }

        }
        
        
        return listaSoluciones;
    }
}
