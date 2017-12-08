import java.io.*;
import java.util.*;

public class ConjDisjuntos{

	 Vertice[] padre;
	 int[] rango;
	 int n;
	 GrafoNoDigirido grafo;

	public ConjDisjuntos(int n, GrafoNoDigirido grafo){
		rango = new Vertice[n];
		padre = new Vertice[n];
		this.n = n;
		this.grafo = grafo;

	}

	public void hacerSet(){

		listaVertices = this.grafo.vertices();

		for (int i=0;i<n;i++){
			padre[i] = listaVertices.get(i);
		}
	}

	public Vertice buscar(Vertice x){

		/*
		listaVertices = this.grafo.vertices();
		int x = 0;
		for (int i=0;i<n;i++){
			if (listaVertices.get(i).getId().equals(v.getId())){
				x = i;
				break;
			}
		}
		*/

		if (this.padre[Integer.parseInt(x.getId())] != listaVertices.get(x.getId())){

			padre[Integer.parseInt(x.getId())] = buscar(padre[Integer.parseInt(x.getId())]);
		}

		return padre[Integer.parseInt(x.getId())];

	}

	public void union(Vertice x, Vertice y){

		Vertice xRaiz = find(x), yRaiz = find(y);

		if (xRaiz.getId().equals(yRaiz.getId())){
			return;
		}

		if (rango[Integer.parseInt(xRaiz.getId())] < rango[Integer.parseInt(yRaiz.getId())] ){
			padre[Integer.parseInt(xRaiz.getId())] = yRoot;
		}
		else if (rango[Integer.parseInt(yRaiz.getId())] < rango[Integer.parseInt(xRaiz.getId())]){
			padre[Integer.parseInt(yRaiz.getId())] = xRoot;
		}
		else{
			padre[Integer.parseInt(yRaiz.getId())] = xRoot;
			rango[Integer.parseInt(xRaiz.getId())] = rango[Integer.parseInt(xRaiz.getId())] + 1;
		}
	}
}