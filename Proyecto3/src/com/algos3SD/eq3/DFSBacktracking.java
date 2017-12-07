import java.util.*;

public class DFSBacktracking{

	LinkedList<LinkedList<Vertice>> listaSoluciones;
	GrafoDirigido grafo;

	public DFSBacktracking(GrafoDirigido grafo){
		listaSoluciones = new LinkedList<LinkedList<Vertice>>();
		this.grafo = grafo;
		List<Vertice> listaVertices = grafo.vertices();

		for (Vertice v : listaVertices){
			LinkedList<Vertice> camino2;
			camino2 = new LinkedList<Vertice>();
			camino2.add(v);
			v.setCaminoHastaEseVertice(camino2);
			busquedaDFS(v);
		}
	}

	public void busquedaDFS(Vertice v){

		LinkedList<Vertice> camino;
		camino = v.getCaminoHastaEseVertice();

		/*INICIO: Halla la cara contraria*/
				String posVerticeV = v.getId();
				int contador = 0;
				/*
				String posX = "";
				String posY = "";
				for (int i=0;i<posVerticeV.length();i++){
					if (posVerticeV.substring(i,i+1).equals("i")){
						posX = posVerticeV.substring(0,i);
						posY = posVerticeV.substring(i+1,posVerticeV.length()-1);
						break;
					}
				}

				String posYInverso = "";
				if (Integer.parseInt(posY)%2==0){
					posYInverso = Integer.toString(Integer.parseInt(posY)+1);
				}
				else{
					posYInverso = Integer.toString(Integer.parseInt(posY)-1);
				}
				*/

				int posX = v.getPosX();
				int posY = v.getPosY();

				int posYInverso;

				if (posY%2==0){
					posYInverso = posY+1;
				}
				else{
					posYInverso = posY-1;
				}

				Vertice[][] cubos = this.grafo.getCubos();
		
				Vertice v1 = cubos[posX][posYInverso];

				/*FIN */

		/* INICIO Revisa los sucesores de esa cara*/
		List<Vertice> listaSucesores = this.grafo.sucesores(v1.getId());

		if (listaSucesores.size()>0){     
			for(Vertice w : listaSucesores){

				



				LinkedList<Vertice> camino1;
				camino1 = new LinkedList<Vertice>();
				for (Vertice iterador : v.getCaminoHastaEseVertice()){
					camino1.add(iterador);
				}
				camino1.add(w);
				w.setCaminoHastaEseVertice(camino1);
				busquedaDFS(w);
			} 
		}

		else{

			//Si no tiene mas sucesores, agregar como a candidato de posibles soluciones

			this.listaSoluciones.add(camino);
		}

		/*FIN*/
		
	}

	public LinkedList<LinkedList<Vertice>> retornaListaSoluciones(){
		return this.listaSoluciones;
	}

/*Itera sobre todas las soluciones y encuentra la torre mas alta*/
	public LinkedList<Vertice> torreMasAlta(LinkedList<LinkedList<Vertice>> posiblesSoluciones){
		int tamano = 0;
		LinkedList<Vertice> solucion;
		solucion = new LinkedList<Vertice>();
		for (LinkedList<Vertice> lista : posiblesSoluciones){
			if (lista.size()>tamano){
				tamano = lista.size();
				solucion = lista;
			}
		}
		return solucion;
	}

/*Funcion que convierte la lista de Vertices que es solucion como un String
dado como en la salida del programa */
	public String formatoSalida(LinkedList<Vertice> solucion){
		String salida ="";
		salida += solucion.size() + "\n";

		for (Vertice v : solucion){
			String posVerticeV = v.getId();
			int contador = 0;

			int posX = v.getPosX();
			int posY = v.getPosY();
			salida+= Integer.toString(posX+1) + " ";
			String cara;
			cara = "";
			if (posY == 0){
				cara = "front";
			}
			else if (posY == 1){
				cara = "back";
			}
			else if (posY == 2){
				cara = "left";
			}

			else if (posY == 3){
				cara = "right";
			}
			else if (posY == 4){
				cara = "top";
			}
			else if (posY == 5){
				cara = "bottom";
			}

			salida+= cara + "\n";

			/*
			String posX = "";
			String posY = "";
			for (int i=0;i<posVerticeV.length();i++){
				if (posVerticeV.substring(i,i+1).equals("i")){
					posX = posVerticeV.substring(0,i);
					posY = posVerticeV.substring(i+1,posVerticeV.length()-1);
					break;
				}
			}

			salida+=posX + " ";
			String cara;
			cara = "";
			if (posY.equals("0")){
				cara = "front";
			}
			else if (posY.equals("1")){
				cara = "back";
			}
			else if (posY.equals("2")){
				cara = "left";
			}

			else if (posY.equals("3")){
				cara = "right";
			}
			else if (posY.equals("4")){
				cara = "top";
			}
			else if (posY.equals("5")){
				cara = "bottom";
			}
			salida+= cara + "\n";

		*/

		}
		
		return salida;
	}

	
}