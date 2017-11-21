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

public class Mesero{
	public static void main(String[] args)throws IOException {

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
			ColaPrioridad q;
			q = new ColaPrioridad();
			miDijkstra = new Dijkstra();
			LinkedList<Vertice> listaPadres;
			//LinkedList<Vertice> listaPadres1;
			List<Vertice> listaDeVertices;
			listaDeVertices = miGrafo.vertices();
			listaPadres = new LinkedList<Vertice>();


			miDijkstra.caminoMasCorto(miGrafo,miGrafo.obtenerNodo(args[1]),q);

			for (Vertice iterador : listaDeVertices){
				LinkedList<Vertice> listaPadres1;
				listaPadres1 = miDijkstra.reconstruirCamino(iterador);
				System.out.print("Nodo " + iterador.getId() + ": ");
				int contador = 0;
				double costo = 0;
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
