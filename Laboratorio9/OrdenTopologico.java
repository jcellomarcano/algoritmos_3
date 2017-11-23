import java.util.LinkedList;
import java.util.List; 
public class OrdenTopologico{

	LinkedList<Vertice> lista;
	double tiempo;

	public OrdenTopologico(){
		lista = new LinkedList<Vertice>();
		tiempo = 0;
	}

	public void ordenacion(GrafoDirigido g){
		List<Vertice> listaVertice;
		listaVertice = g.vertices();

		for (Vertice iterador : listaVertice){
			iterador.setVisitado(false);
			iterador.setPadre(null);
		}

		for (Vertice iterador2 : listaVertice){
			if (!iterador2.getVisitado()){
				topologicoVisitar(iterador2,g);
			}
		}
	}

	public void topologicoVisitar(Vertice u, GrafoDirigido g){
		u.setVisitado(true);
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
	}

	public LinkedList<Vertice> getLista(){
		return this.lista;
	}
}