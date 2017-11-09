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
		GrafoNoDirigido miGrafo;
		miGrafo	= new GrafoNoDirigido();
		miGrafo.cargarGrafo("EjemploA.txt");
		System.out.println(miGrafo.toString());
	}
}