import java.util.LinkedList;
import java.util.List;

public class Charcos {
    List<List<Vertice>> componentes;
    GrafoDirigido grafo;
    LinkedList<List<Vertice>> inundados;

    public Charcos(List<List<Vertice>> componentes, GrafoDirigido grafo){
        this.componentes = componentes;
        this.grafo = grafo;
        this.inundados = new LinkedList<List<Vertice>>();
    }

    public LinkedList<List<Vertice>> getInundados() {
        
        externo:for (List<Vertice> iterador:componentes) {
            for (Vertice k:
                 ) {
                
            }
        }
        
        
        return inundados;
    }
}
