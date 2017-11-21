public class ColaPrioridad{

	private Elemento cabeza;
	private int tamano;

	public ColaPrioridad(){
		this.cabeza = null;
		this.tamano = 0;
	}

	public void agregarElemento(Elemento e){
		Elemento iterador;
		iterador = cabeza;
		int contador = 0;

		while (contador < tamano-1){
			if (iterador.getPrioridad()>=e.getPrioridad()){
				break;
			}
			iterador = iterador.getSiguiente();
			contador++;
		}

		if (contador == 0 && tamano!=0){
			Elemento aux;
			aux = cabeza;
			cabeza = e;
			e.setSiguiente(aux);
		}
		else if(tamano==0){
			cabeza = e;
		}
		else{

			if (contador==tamano){
				iterador.setSiguiente(e);
			}

			else{
				Elemento aux;
				aux = iterador.getSiguiente();
				iterador.setSiguiente(e);
				iterador.getSiguiente().setSiguiente(aux);
			}
			
		}
		
		tamano++;
	}

	public Elemento extraerMinimo(){
		Elemento aux;
		Elemento aux2;
		aux = cabeza;
		aux2 = cabeza.getSiguiente();
		cabeza = aux2;
		tamano--;
		return aux;
	}

	public void imprimirCola(){
		Elemento iterador;
		int contador = 0;

		iterador = cabeza;
		while(contador!=tamano){
			System.out.println(iterador.getNodo().getVertice().getId());
			iterador = iterador.getSiguiente();
			contador++;
		}
	}

	public boolean estaEnLaCola(Vertice v){

		int contador = 0;
		boolean seEncuentra = false;
		Elemento iterador;
		iterador = this.cabeza;

		if (iterador!=null){
			while (contador!=this.tamano){
				System.out.println(iterador.getNodo().getVertice().getId());
			if (iterador.getNodo().getVertice().getId().equals( v.getId())){
				seEncuentra = true;
				return seEncuentra;
			}
			iterador = iterador.getSiguiente();
			contador++;
		}
		}
		

		return seEncuentra;
	}

	public int getTamano(){
		return this.tamano;
	}
}