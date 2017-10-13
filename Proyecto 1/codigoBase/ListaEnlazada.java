public class ListaEnlazada{

	private Nodo cabeza;
	private int tamano;

	public ListaEnlazada(){
		cabeza = null;
		tamano = 0;
	}

	public void agregarAlInicio(Vertice vertice){
		tamano = tamano + 1;
		if(cabeza == null){
			cabeza = new Nodo(vertice);
		}

		else{
			Nodo temporal = cabeza;
			Nodo nodoAAgregar = new Nodo(vertice);
			nodoAAgregar.enlazarAOtroNodo(temporal);
			cabeza = nodoAAgregar;
		}
	}

	public Nodo getElemento(int posicion){
		int contador=1;
		Nodo iterador = cabeza;

		while (contador!=posicion && contador!=tamano){
			iterador = iterador.getSiguiente();
			contador++;
		}

		return iterador;
	}

	public Nodo getNodoVerticePorId(String id){
		int contador=1;
		Nodo iterador = cabeza;
		Nodo nodoVerticeBuscado;

		nodoVerticeBuscado = iterador;
		while (contador!=tamano){
			iterador = iterador.getSiguiente();
			contador++;

			if (iterador.getVertice().getId().equals(id)){
				nodoVerticeBuscado = iterador;
			}
		}

		return nodoVerticeBuscado;
	}

	public void agregarAlFinal(Vertice vertice){
		int contador = 1;
		Nodo iterador = cabeza;
		Nodo nodoAAgregar;


		tamano = tamano + 1;
		

		nodoAAgregar = new Nodo(vertice);

		if(cabeza == null){
			cabeza = nodoAAgregar;
		}

		else{
			while(contador!=tamano-1){
				iterador = iterador.getSiguiente();
				contador++;
			}
			iterador.enlazarAOtroNodo(nodoAAgregar);
		}	
	}


	public int getTamano(){
		return tamano;
	}

	public void agregarNodoRelacionado(Nodo nodoDeVerticeInicial, Nodo nodoDeVerticeFinal){
		Nodo iterador;

		iterador = nodoDeVerticeInicial.getRelacion();
		if (nodoDeVerticeInicial.getRelacion()==null){
			nodoDeVerticeInicial.setRelacionados(nodoDeVerticeFinal);
			System.out.println("Hola");
		}

		else{
			while (iterador.getSiguiente()!=null){
				System.out.println("Entro en este caso");
				iterador=iterador.getSiguiente();
			}
			iterador.enlazarAOtroNodo(nodoDeVerticeFinal);
		}
	}

	public void imprimirLista(){
		Nodo iteradorListaPrincipal;
		Nodo iteradorListaSecundaria;

		iteradorListaPrincipal = cabeza;
		

		while(iteradorListaPrincipal!=null){
			iteradorListaSecundaria = iteradorListaPrincipal.getRelacion();
			System.out.print(iteradorListaPrincipal.getVertice().toString() + "--->");
			while(iteradorListaSecundaria!=null){
				System.out.print(iteradorListaSecundaria.getVertice().toString() + "--->");
				iteradorListaSecundaria = iteradorListaSecundaria.getSiguiente();
			}
			iteradorListaPrincipal = iteradorListaPrincipal.getSiguiente();
			System.out.println();
		}

	}
}