public class Nodo{

	private Vertice vertice;
	private Nodo relacion;
	private Lado lado;
	private Nodo siguienteRelacion;

	public Nodo(Vertice vertice){
		this.vertice = vertice;
	}

	public Nodo(Vertice vertice,Lado lado){
		this.vertice = vertice;
		this.lado = lado;
	}

	public void setRelacion(Nodo relacion){
		this.relacion = relacion;
	}

	public Vertice getVertice(){
		return vertice;
	}

	public Nodo getRelacion(){
		return relacion;
	}

	public Lado getLado(){
		return lado;
	}

	public void setSiguiente(Nodo nodo){
		siguienteRelacion = nodo;
	}

	public Nodo getSiguiente(){
		return siguienteRelacion;
	}
}