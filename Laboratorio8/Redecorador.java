import java.util.*;
import java.lang.Exception;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List; 

public class Redecorador{

	public static void main(String[] args) throws IOException{
		
		if (args.length!=2){
			System.out.println("El numero de argumentos es incorrecto");
			System.exit(1);
		}

		else{

			GrafoNoDirigido miGrafo;
			miGrafo	= new GrafoNoDirigido();
			miGrafo.cargarGrafo(args[0]);
			Nodo nodoRaiz;
			Dijkstra miDijkstra;
			Aestrella miAestrella;
			ColaPrioridad q;
			q = new ColaPrioridad();
			miAestrella = new Aestrella();
			miDijkstra = new Dijkstra();
			LinkedList<Vertice> listaPadres;
			//LinkedList<Vertice> listaPadres1;
			List<Vertice> listaDeVertices;
			listaDeVertices = miGrafo.vertices();
			listaPadres = new LinkedList<Vertice>();
			LinkedList<Vertice> listaPadres2;


			miDijkstra.caminoMasCorto(miGrafo,miGrafo.obtenerNodo(args[1]),q);


				
				
				LinkedList<Vertice> listaPadres1;


			int contador = 0;
			double costo = 0;


			System.out.println("Aqui voy a intentar imprimir los caminos de mi A*!:");
			for (Vertice iterador : listaDeVertices){
				miAestrella.caminoMasCorto(miGrafo,miGrafo.obtenerNodo(args[1]),miGrafo.obtenerNodo(iterador.getId()));
				listaPadres2 = miAestrella.reconstruirCamino(iterador);
				System.out.print("Nodo " + iterador.getId() + ": ");
				contador = 0;
				costo = 0;
				while(listaPadres2.size()!=0){
					Vertice elemento = listaPadres2.removeLast();
					costo += elemento.getPeso();
					System.out.print(elemento.getId()+"->");
					contador++;
				}
				System.out.print(iterador.getId());
				System.out.println();
			}

			System.out.println();
			System.out.println();
			System.out.println("Aqui empiezo a imprimir lo de Dijkstra del lab pasado: ");

			for (Vertice iterador : listaDeVertices){
				listaPadres1 = miDijkstra.reconstruirCamino(iterador);
				System.out.print("Nodo " + iterador.getId() + ": ");
				contador = 0;
				costo = 0;
				while(listaPadres1.size()!=0){
					Vertice elemento = listaPadres1.removeLast();
					costo += elemento.getPeso();
					System.out.print(elemento.getId()+"->");
					contador++;
				}
				costo += iterador.getPeso(); 
				System.out.print(iterador.getId() + "\t" + contador + " lados" + " (costo " + costo + ")");

				System.out.println();
			}

		

			}
	}
}