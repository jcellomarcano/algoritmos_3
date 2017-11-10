import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra{

	double infinito;


	public Dijkstra(){
		infinito = Double.POSITIVE_INFINITY; 
	}

	public void caminoMasCorto(GrafoNoDirigido g, Nodo s,ColaPrioridad cola){
		List<Vertice> listaVertices;
		listaVertices = g.vertices();
		Nodo iterador;
		Vertice sv;
		sv = s.getVertice();
		Elemento e;

		for (Vertice iterador5 : listaVertices){
			iterador5.setPeso(this.infinito);
			iterador5.setPadre(null);
			iterador5.setVisto(false);
		}

		sv.setPeso(0);
		e = new Elemento(s, s.getVertice().getPeso());
		cola.agregarElemento(e);

		while (cola.getTamano()>0){
			Elemento u;
			Vertice uv;
			List<Vertice> adyacentes;
			
			u = cola.extraerMinimo();
			uv = u.getNodo().getVertice();
			uv.setVisto(true);
			adyacentes = g.adyacentes(uv.getId());
			
			for (int i=0;i<adyacentes.size();i++){

				Vertice v;
				v = adyacentes.get(i);
				Arista a;
				a = g.obtenerArista(v.getId()+uv.getId());
				if (a==null){
					a = g.obtenerArista(uv.getId()+v.getId());
				}


				if ((!(v.getVisto())) && (v.getPeso() > uv.getPeso() + a.getPeso())){
					Elemento elementoAAgregarALaCola;
					Nodo vn;
					vn = new Nodo(v);
					elementoAAgregarALaCola = new Elemento(vn,v.getPeso());
					v.setPeso(uv.getPeso() + a.getPeso());
					v.setPadre(uv);
					cola.agregarElemento(elementoAAgregarALaCola);
				}
			}

		}



	}

	public LinkedList<Vertice> reconstruirCamino(Vertice s,LinkedList<Vertice> listaDePadres){
		while (s.getPadre()!=null){
			System.out.println("Soy su papa!: " + s.getPadre().getId());
			listaDePadres.add(s.getPadre());
			s = s.getPadre();
			reconstruirCamino(s,listaDePadres);
		}
		return listaDePadres;

	}
}