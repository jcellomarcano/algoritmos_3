import java.util.*;
import java.io.*;

public class DFSBacktracking{

	int tamanoCaminoMasLargo;
	LinkedList<Vertice> caminoMasLargo;
	GrafoDirigido grafo;


	public DFSBacktracking(GrafoDirigido grafo){

		tamanoCaminoMasLargo = 0;
		
		this.grafo = grafo;
		List<Vertice>listaVertices = grafo.vertices();
		caminoMasLargo = new LinkedList<Vertice>();
		if (listaVertices.size()>0){
			
			for (Vertice v : listaVertices){
				LinkedList<Vertice> pila;
				LinkedList<Vertice> camino;
				int[] materiales = v.getMateriales(	);
				for (int i=0;i<=5;i++){
					pila = new LinkedList<Vertice>();
					camino = new LinkedList<Vertice>();
					//System.out.println("Probando colocando el primer cubo de cierta forma");
					v.setCaraArriba(i);
					v.setMaterialCaraArriba(materiales[i]);
					camino.add(v);
					v.setCaminoHastaEseVertice(camino);
					busquedaDFSBacktracking(v,pila,camino);
				}
				
				
			}
		}

	}

	public void busquedaDFSBacktracking(Vertice v,LinkedList<Vertice> pila,LinkedList<Vertice> camino){
		
		camino = v.getCaminoHastaEseVertice();		
		//System.out.println("Mi camino va siendo: " + camino);

		
		List<Vertice> sucesores = this.grafo.sucesores(v.getId());
		//System.out.println("Mis sucesores son: " + sucesores);
		if (sucesores.size() > 0){
			for (Vertice w : sucesores){
				w.setCaminoHastaEseVertice(camino);
				pila.addFirst(w);
			}

			if (pila.size()>0){
				v = pila.pollFirst();
				LinkedList<Integer> listaCarasConexion;
				listaCarasConexion = v.getCaraConexion();
				int cara = listaCarasConexion.pollFirst();
				int caraArriba;
				if (cara%2 ==0){
					caraArriba = cara;
				}
				else{
					caraArriba = cara;
				}
				int[] materiales = v.getMateriales();
				int material = materiales[caraArriba];
				v.setCaraArriba(caraArriba);
				v.setMaterialCaraArriba(material);
				System.out.println("Estoy apilando el cubo " + v.getId() + " con la cara " + v.getCaraArriba() + " y el material " + v.getMaterialCaraArriba() + " arriba sobre el cubo " + camino.getLast().getId() + " que tiene el material arriba de: " + camino.getLast().getMaterialCaraArriba());
				camino.add(v);
				
				busquedaDFSBacktracking(v,pila,camino);
			}
		}

		else{

			if (camino.size()>tamanoCaminoMasLargo){
				//System.out.println("HOLAAAA");
				//System.out.println("Tengo tamano " + tamanoCaminoMasLargo);
				caminoMasLargo = new LinkedList<Vertice>();
				for (Vertice iterador : camino){
					caminoMasLargo.add(iterador);
				}
				tamanoCaminoMasLargo = caminoMasLargo.size();
			}
			
			if (pila.size()>0){
				camino.pollLast();
				v = pila.pollFirst();
				busquedaDFSBacktracking(v,pila,camino);
			}

		}

		
		
	}

	public LinkedList<Vertice> retornarCaminoMasLargo(){
		return this.caminoMasLargo;
	}
}