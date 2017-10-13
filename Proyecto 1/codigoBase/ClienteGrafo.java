import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ClienteGrafo{

public static void main(String[] args) throws IOException{

	/* Prueba de la clase Vertice, Lado, Arco y Arista*/

	Vertice miVerticeInicial;
	Vertice miVerticeFinal;
	Vertice miVerticeU;
	Vertice miVerticeV;
	Vertice miVerticeW;
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
	System.out.println(miGrafoNoDirigido.numeroDeVertices());
	System.out.println(miGrafoNoDirigido.numeroDeLados());

	ListaEnlazada miListaEnlazada;
	ListaEnlazada miOtraListaEnlazada;
	miListaEnlazada = new ListaEnlazada();
	miListaEnlazada.agregarAlInicio(miVerticeInicial);
	miListaEnlazada.agregarAlFinal(miVerticeFinal);
	miListaEnlazada.agregarAlFinal(miVerticeU);
	miListaEnlazada.agregarAlFinal(miVerticeV);
	miListaEnlazada.agregarAlInicio(miVerticeW);
	System.out.println(miListaEnlazada.getTamano());



	Nodo unNodo = new Nodo(miVerticeU);
	Nodo segundoNodo = new Nodo(miVerticeV);
	Nodo tercerNodo;
	Nodo cuartoNodo;
	Vertice otroVertice;
	Vertice unVerticeMas;

	unNodo.enlazarAOtroNodo(segundoNodo);

	tercerNodo = unNodo.getSiguiente();

	otroVertice = tercerNodo.getVertice();

	System.out.println(otroVertice.getId());

	cuartoNodo = miListaEnlazada.getElemento(2);

	unVerticeMas = cuartoNodo.getVertice();

	System.out.println(unVerticeMas.getId());

 	System.out.println();
 	System.out.println();

 	miOtraListaEnlazada = miGrafoNoDirigido.getListaAdyacencia();

 	System.out.println(miOtraListaEnlazada.getElemento(4).getVertice().getPeso());

 	System.out.println(miOtraListaEnlazada.getElemento(3).getRelacion().getSiguiente().getLado().getPeso());

 	System.out.println(miOtraListaEnlazada.getNodoVerticePorId("40").getVertice().getPeso());

 	System.out.println();
 	System.out.println();

 	miOtraListaEnlazada.imprimirLista();
}
}