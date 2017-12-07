import java.util.LinkedList;
import java.util.List; 
public class OrdenTopologico{

	LinkedList<Vertice> lista;
	double tiempo;

	public OrdenTopologico(){
		lista = new LinkedList<Vertice>();
	}

	public void ordenacion(GrafoDirigido g){
		List<Vertice> listaVertice;
		listaVertice = g.vertices();


        for (Vertice iterador : listaVertice){
            iterador.setPadre(null);
        }
        tiempo = 0;

        for (Vertice iterador2 : listaVertice){
            if (!iterador2.getVisitado()){
                topologicoVisitar(iterador2,g);
            }
        }
    }

	public void topologicoVisitar(Vertice u, GrafoDirigido g){
		u.setVisitado(true);
		LinkedList<Vertice> terminados = new LinkedList<Vertice>();
		this.tiempo = this.tiempo + 1;
		u.setPeso(tiempo);
		for (Vertice v : g.sucesores(u.getId())){
            if (!v.getVisitado()){
				v.setPadre(u);
				topologicoVisitar(v,g);
			}
		}

		u.setTerminado(true);
		tiempo = tiempo + 1;
		u.setPeso(tiempo);
		lista.add(u);
		terminados.add(u);
	}

	public LinkedList<Vertice> getLista(){
		return this.lista;
	}
}