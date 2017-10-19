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



}
}