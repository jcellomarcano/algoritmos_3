public class Dijkstra(){

	public Dijkstra(){
		double infinito = Double.positive_infinity; 
	}

	public caminoMasCorto(GrafoNoDirigido g, Nodo s,ColaPrioridad cola){
		List<Vertice> listaVertices;
		listaVertices = g.vertices();
		Vertice iterador;
		iterador = listaVertices.get(0);
		Vertice sv;
		sv = s.getVertice();
		Elemento e;

		while (iterador!=null){
			iterador.setPeso(infinito);
			iterador.setPadre(null);
			iterador.setVisto(false);
			iterador = iterador.getSiguiente();
		}

		sv.setPeso(0);
		e = new Elemento(s, s.getVertice.getPeso());
		cola.agregarElemento(e);

		while (cola.getTamano()>0){
			Elemento u;
			u = cola.extraerMinimo();
		}



	}
}