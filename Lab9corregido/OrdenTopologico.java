import java.util.LinkedList;
import java.util.List; 
public class OrdenTopologico{
    /*
    * Se edito el atributo de lista, en vez de Vertice, un String*/
	LinkedList<String> lista;
	double tiempo;

	public OrdenTopologico(){
		lista = new LinkedList<String>();
	}

	public void ordenacion(GrafoDirigido g){
		List<Vertice> listaVertice;
		listaVertice = g.vertices();
		//System.out.println(listaVertice);


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
		if (!lista.contains(u.getId())) {//agregado if statement para corroborar quien agrega
            lista.add(u.getId());
            terminados.add(u);
        }
	}

	public LinkedList<String> getLista(){
		return this.lista;
	}
}