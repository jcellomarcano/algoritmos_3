/**
 * 
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GrafoNoDirigido implements Grafo
{

    private int nVertices;
    private int nLados;
    private ListaEnlazada listaDeAdyacencias;

    public GrafoNoDirigido() {
    }

    public boolean cargarGrafo(String dirArchivo) throws IOException{
        /*Abrir y leer archivo txt, se va leyendo linea por linea*/
        BufferedReader in = new BufferedReader(new FileReader(dirArchivo));

        nVertices = Integer.parseInt(in.readLine());
        nLados = Integer.parseInt(in.readLine());

        listaDeAdyacencias = new ListaEnlazada();

        for (int i=0; i<nVertices; i++){
            String linea = in.readLine();
            String idDeNuevoVertice;
            int pesoNuevoVertice;
            Vertice nuevoVertice;
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            idDeNuevoVertice = palabra;
            palabra = st.nextToken();
            pesoNuevoVertice = Integer.parseInt(palabra);
            nuevoVertice = new Vertice(idDeNuevoVertice,pesoNuevoVertice);
            listaDeAdyacencias.agregarAlFinal(nuevoVertice);
        }

        for (int i=0; i<nLados; i++){
            String linea = in.readLine();
            String idDeNuevoLado;
            String idVerticeARelacionar;
            int pesoNuevoLado;
            Arista nuevaArista;
            Nodo nuevoNodoVerticeInicial;
            Vertice nuevoVerticeInicial;
            Vertice nuevoVerticeFinal;
            Nodo nuevoNodo;
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            idDeNuevoLado = palabra;
            palabra = st.nextToken();
            idVerticeARelacionar = palabra;
            nuevoNodoVerticeInicial = listaDeAdyacencias.getNodoVerticePorId(idVerticeARelacionar);
            nuevoVerticeInicial = listaDeAdyacencias.getNodoVerticePorId(idVerticeARelacionar).getVertice();
            palabra = st.nextToken();
            idVerticeARelacionar = palabra;
            nuevoVerticeFinal = listaDeAdyacencias.getNodoVerticePorId(idVerticeARelacionar).getVertice();
            palabra = st.nextToken();
            pesoNuevoLado = Integer.parseInt(palabra);
            nuevaArista = new Arista(idDeNuevoLado,pesoNuevoLado,nuevoVerticeInicial,nuevoVerticeFinal);
            nuevoNodo = new Nodo (nuevoVerticeFinal,nuevaArista);

            listaDeAdyacencias.agregarNodoRelacionado(nuevoNodoVerticeInicial,nuevoNodo);
            //nuevoNodoVerticeInicial.setRelacionados(nuevoNodo);
        }

        boolean variable = true;
        return variable;
    }

    public ListaEnlazada getListaAdyacencia(){
        return listaDeAdyacencias;
    }
    
    public int numeroDeVertices() {
        return nVertices;
    }

    
    public int numeroDeLados() {
        return nLados;
    }
    /*
    public boolean agregarVertice(Vertice v) {
    }

    public boolean agregarVertice(String id, double peso) {
    }
    
    public Vertice obtenerVertice(String id) {
    }

    public boolean estaVertice(String id) {
    }

    public boolean estaLado(String u, String v){
    }

    public boolean eliminarVertice(String id) {
    }

    public List<Vertice> vertices() {
    }

    public List<Lado> lados() {
    }

    public int grado(String id) {
    }

    public List<Vertice> adyacentes(String id) {
    }
 
    public List<Lado> incidentes(String id) {
    }

    public Object clone() {
    }

    public String toString() {
    }

    public boolean agregarArista(Arista a) {
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
    }

    public boolean eliminarArista(String id) {
    }

    public Arista obtenerArista(String id) {
    }

    */
}
