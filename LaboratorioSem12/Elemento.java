public class Elemento{

	private Nodo nodo;
	private Elemento siguiente;
	private double prioridad;

	public Elemento(Nodo nodo,double prioridad){
		this.nodo = nodo;
		this.prioridad = prioridad;
		this.siguiente = null;
	}

	public Nodo getNodo(){
		return this.nodo;
	}

	public double getPrioridad(){
		return this.prioridad;
	}

	public void setSiguiente(Elemento e){
		this.siguiente = e;
	}

	public Elemento getSiguiente(){
		return this.siguiente;
	}

}