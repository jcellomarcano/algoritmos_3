import java.util.LinkedList;
import java.util.List; 
public class OrdenTopologico{

	LinkedList<Vertice> lista;
	double tiempo;
	GrafoDirigido g;

	public OrdenTopologico(GrafoDirigido g){
		lista = new LinkedList<Vertice>();
		tiempo = 0;
		this.g = g;
	}

	public void ordenacion(){
		List<Vertice> listaVertice;
		listaVertice = this.g.vertices();

		/*
		for (Vertice iterador : listaVertice){
			iterador.setVisitado(false);
			iterador.setPadre(null);
		}
		*/

		for (int i=0;i<listaVertice.size();i++){
			if (!listaVertice.get(i).getVisitado()){
				topologicoVisitar(listaVertice.get(i));
			}
		}
	}

	public void topologicoVisitar(Vertice u){
		System.out.println("Ahora soy el vertice u y ahora soy visitado: "+System.identityHashCode(u)+" y mi nombre es: "+ u);
		u.setVisitado(true);
		System.out.println("Ahora mi estado es de: " + u.getVisitado());
		this.tiempo = this.tiempo + 1;
		u.setPeso(tiempo);
		List<Vertice> sucesoresDeU;
		sucesoresDeU = this.g.sucesores(u.getId());
		for (int i=0;i<sucesoresDeU.size();i++){
			System.out.println("Soy el vertice v a revisar: "+ System.identityHashCode(sucesoresDeU.get(i))+"y mi nombre es: "+sucesoresDeU.get(i));
			System.out.println("y mi padre es: "+ sucesoresDeU.get(i).getPadre());
			System.out.println();
			System.out.println();
			Vertice hola;
			hola = this.g.obtenerVertice(sucesoresDeU.get(i).getId());
			System.out.println("Soy hola: " + hola);
			System.out.println("y mi codigo de hola es: "+ System.identityHashCode(hola));
			if (sucesoresDeU.get(i).getVisitado()==false){
				System.out.println("Soy el vertice v y no he sido visitado: "+ sucesoresDeU.get(i));
				System.out.println("Y no he sido visitado ya que mi estado es: "+sucesoresDeU.get(i).getVisitado());
				sucesoresDeU.get(i).setPadre(u);
				topologicoVisitar(sucesoresDeU.get(i));
			}
		}

		System.out.println("Soy el vertice u que va a ser agregado: " + u);

		u.setTerminado(true);
		tiempo = tiempo + 1;
		u.setPeso(tiempo);
		lista.add(u);
	}

	public LinkedList<Vertice> getLista(){
		return this.lista;
	}
}