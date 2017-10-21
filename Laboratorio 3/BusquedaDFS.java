/**
*BusquedaDFS.java
*Algoritmo de busqueda de alcance de profundidad
*Trabajo de
*@author Jesús Marcano 12-10359
*@author Jose Basanta 13-10125
*/
import java.util.*;

public class BusquedaDFS{
	Stack<Integer> salida;
	LinkedList<Integer> descubiertos;
	ArrayList<Integer> predecesoresDFS;

	public 	BusquedaDFS(GrafoDirigido G) {
		dfs(G);
		descubiertos = new LinkedList<Integer>();
		predecesoresDFS = new ArrayList<Integer>(G.numeroDeVertices());
		for (int i = 0; i < G.numeroDeVertices(); i++) {
			predecesoresDFS.add(-1);
		}
	}

	public void dfs(GrafoDirigido G) {
		salida = new Stack<Integer>();

		salida.push(0);
		while (!salida.isEmpty()) {
			int v = salida.pop();
			if (descubiertos!=null && !descubiertos.contains(v)) {
				descubiertos.add(v);
				for (int vert : G.adyacentes(Integer.toString(v))) {
					salida.push(vert);
					predecesoresDFS.set(vert, v);
				}
			}
		}
	}

	public int[] arcsVisited(int v) {
		int[] visitados = new int[predecesoresDFS.size()];
		for (int i=0; i < predecesoresDFS.size(); i++) {
			visitados[i] = predecesoresDFS.get(i);
		}
		return visitados;

	}

	public ArrayList<Integer> getPath(int s, int v) {
		int temp = v;
		Stack<Integer> camino = new Stack<Integer>();
		ArrayList<Integer> caminoLista = new ArrayList<Integer>();

		while (temp != s || temp != -1) {
			System.out.println(temp);
			camino.push(temp);
			temp = predecesoresDFS.get(temp);
		}

		if (temp == s) {
			camino.push(temp);
			while (!camino.isEmpty()) {
				caminoLista.add(camino.pop());
			}
		}

		return caminoLista;
	}

	public boolean hasPath(int s, int v) {
		ArrayList<Integer> comprobacion = new ArrayList<Integer>();

		if (getPath(s, v).equals(comprobacion)) {
			return false;
		}
		else {
			return true;
		}
	}

	public void printPath(int s, int v) {
		System.out.println(getPath(s, v).toString());
	}
}


