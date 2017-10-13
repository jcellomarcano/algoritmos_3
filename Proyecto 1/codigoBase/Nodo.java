public class Nodo{

	private Vertice vertice;
	private Lado lado;
	private Nodo siguiente;
	private Nodo relacionados;

	public Nodo(Vertice vertice){
		this.vertice = vertice;
		this.siguiente = null;
		this.relacionados = null;
	}

	public Nodo(Vertice vertice, Lado lado){
		this.vertice = vertice;
		this.siguiente = null;
		this.lado = lado;
	}

	public void enlazarAOtroNodo(Nodo siguiente){
		this.siguiente = siguiente;
	}

	public Nodo getSiguiente(){
		return siguiente;
	}

	public Vertice getVertice(){
		return vertice;
	}

	public Lado getLado(){
		return lado;
	}

	public void setRelacionados(Nodo relacionados){
		this.relacionados = relacionados;
	}

	public Nodo getRelacion(){
		return relacionados;
	}
}