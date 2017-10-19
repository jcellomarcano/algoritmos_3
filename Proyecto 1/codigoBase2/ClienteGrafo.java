/**
 * ClienteGrafo.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

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

	GrafoNoDirigido miGrafoNoDirigido;
	miGrafoNoDirigido = new GrafoNoDirigido();
	miGrafoNoDirigido.cargarGrafo("prueba1.txt");
	System.out.println(miGrafoNoDirigido.toString());
	System.out.println();
	System.out.println();
	miGrafoNoDirigido.eliminarVertice("10");
	System.out.println(miGrafoNoDirigido.toString());
	System.out.println();
	System.out.println();
	miGrafoNoDirigido.agregarVertice("buenas",20);
	System.out.println(miGrafoNoDirigido.toString());
	miGrafoNoDirigido.eliminarVertice("buenas");
	System.out.println(miGrafoNoDirigido.toString());
	System.out.println();
	System.out.println();

	GrafoNoDirigido clonDeMiGrafo;
	clonDeMiGrafo = new GrafoNoDirigido();
	clonDeMiGrafo = miGrafoNoDirigido.clone();

	System.out.println("Este es mi clon: ");
	System.out.println(clonDeMiGrafo.toString());
	System.out.println("Agrego un vertice");
	clonDeMiGrafo.agregarVertice("hola",20);
	System.out.println();
	System.out.println("Este es mi clon: ");
	System.out.println(clonDeMiGrafo.toString());
	System.out.println();
	System.out.println(miGrafoNoDirigido.toString());
	System.out.println();
	System.out.println();

	GrafoDirigido miGrafoDirigido;
	miGrafoDirigido = new GrafoDirigido();
	miGrafoDirigido.cargarGrafo("prueba1.txt");
	System.out.println(miGrafoDirigido.toString());
	System.out.println();
	System.out.println();
	System.out.println("Creare mi clon de GrafoDirigido");
	GrafoDirigido clonDeMiGrafoDirigido;
	//clonDeMiGrafoDirigido = new GrafoDirigido();
	clonDeMiGrafoDirigido = miGrafoDirigido.clone();
	System.out.println(miGrafoDirigido.toString());
	System.out.println("Voy a agregarVertice");
	clonDeMiGrafoDirigido.agregarVertice("bien",20);
	System.out.println(clonDeMiGrafoDirigido.toString());

	


}
}