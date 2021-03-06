import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClienteGrafo{

public static void main(String[] args) throws IOException{

	/* Prueba de la clase Vertice, Lado, Arco y Arista*/

	Vertice miVerticeInicial;
	Vertice miVerticeFinal;
	Vertice miVerticeU;
	Vertice miVerticeV;
	Vertice miVerticeW;
	Vertice verticeAgregado;
	Arco miArco;
	Arista miArista;

	miVerticeInicial = new Vertice("inicial",50);
	miVerticeFinal = new Vertice("final",60);
	miVerticeU = new Vertice("U",90);
	miVerticeV = new Vertice("V",100);
	miVerticeW = new Vertice("W",500);

	miArco = new Arco("arco1",70,miVerticeInicial,miVerticeFinal);
	System.out.println(miArco.toString());

	miArista= new Arista("arista1",80,miVerticeU,miVerticeV);
	System.out.println(miArista.toString());

	/*Prueba de la interfaz Grafo*/

	/*Prueba Grafo no dirigido*/

	GrafoNoDirigido miGrafoNoDirigido;
	miGrafoNoDirigido = new GrafoNoDirigido();
	miGrafoNoDirigido.cargarGrafo("prueba1.txt");

	LinkedList<Nodo> unaLista = new LinkedList<Nodo>();
	Nodo unNodo;
	Vertice unVertice;

	unaLista = miGrafoNoDirigido.getListaAdyacencia();
	unNodo = unaLista.getLast();
	unVertice = unNodo.getVertice();
	System.out.println(unVertice.toString());

	System.out.println(miGrafoNoDirigido.obtenerVertice("40").toString());

	System.out.println(miGrafoNoDirigido.getListaAdyacencia().getFirst().getRelacion().getLado());

	System.out.println();
	System.out.println();
	System.out.println(miGrafoNoDirigido.toString());
}
}