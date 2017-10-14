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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GrafoNoDirigido implements Grafo{

    private int nVertices;
    private int nLados;
    private LinkedList<Nodo> listaDeAdyacencias = new LinkedList<Nodo>();

    public GrafoNoDirigido() {

    }

    public boolean cargarGrafo(String dirArchivo) throws IOException{
        /*Abrir y leer archivo txt, se va leyendo linea por linea*/
        BufferedReader in = new BufferedReader(new FileReader(dirArchivo));
        boolean seCargo=false;
        nVertices = Integer.parseInt(in.readLine());
        nLados = Integer.parseInt(in.readLine());

        for (int i=0; i<nVertices; i++){
            String linea = in.readLine();
            String idDeNuevoVertice;
            int pesoNuevoVertice;
            Vertice nuevoVertice;
            Nodo nuevoNodo;
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            idDeNuevoVertice = palabra;
            palabra = st.nextToken();
            pesoNuevoVertice = Integer.parseInt(palabra);
            nuevoVertice = new Vertice(idDeNuevoVertice,pesoNuevoVertice);
            nuevoNodo = new Nodo(nuevoVertice);
            listaDeAdyacencias.add(nuevoNodo);

        }

        for (int i=0; i<nLados; i++){
            String linea = in.readLine();
            String idDeNuevoLado;
            String idVerticeARelacionar;
            int pesoNuevoLado;
            Arista nuevaArista;
            Nodo nuevoNodoVerticeInicial;
            Nodo nuevoNodoVerticeFinal;
            Vertice nuevoVerticeInicial;
            Vertice nuevoVerticeFinal;
            Nodo nuevoNodo;
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            idDeNuevoLado = palabra;
            palabra = st.nextToken();
            idVerticeARelacionar = palabra;
            nuevoNodoVerticeInicial = null;
            nuevoNodoVerticeFinal = null;
            for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();) {
                Nodo item = j.next();
                if (item.getVertice().getId().equals(idVerticeARelacionar)){
                    nuevoNodoVerticeInicial = item;
                    }
            }

            nuevoVerticeInicial = nuevoNodoVerticeInicial.getVertice();
            palabra = st.nextToken();
            idVerticeARelacionar = palabra;

            for (Iterator<Nodo> k = listaDeAdyacencias.iterator(); k.hasNext();) {
                Nodo item = k.next();
                if (item.getVertice().getId().equals(idVerticeARelacionar)){
                    nuevoNodoVerticeFinal = item;
                    }
            }

            nuevoVerticeFinal = nuevoNodoVerticeFinal.getVertice();

            palabra = st.nextToken();
            pesoNuevoLado = Integer.parseInt(palabra);
            nuevaArista = new Arista(idDeNuevoLado,pesoNuevoLado,nuevoVerticeInicial,nuevoVerticeFinal);
            nuevoNodo = new Nodo (nuevoVerticeFinal,nuevaArista);
                if (nuevoNodoVerticeInicial.getRelacion()==null){
                    nuevoNodoVerticeInicial.setRelacion(nuevoNodo);
                    System.out.println("Acabo de agregar a: " + nuevoNodo.getVertice().toString());
                }
                else{
                    Nodo iterador;
                    iterador = nuevoNodoVerticeInicial.getRelacion();
                    while (iterador.getSiguiente()!=null){
                        iterador = iterador.getSiguiente();
                    } 
                    iterador.setSiguiente(nuevoNodo);
                    System.out.println("Acabo de agregar a: " + nuevoNodo.getVertice().toString());
                }

        }

        return seCargo;
    }

    public Nodo obtenerNodo(String id){
        Nodo nodoDeVerticeEncontrado;
        Vertice verticeEncontrado;
        nodoDeVerticeEncontrado = null;
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
            Nodo item = i.next();
            if (item.getVertice().getId().equals(id)){
                nodoDeVerticeEncontrado = item;
            }

        }
        return nodoDeVerticeEncontrado;
    }

    public Vertice obtenerVertice(String id) {
        Nodo nodoDeVerticeEncontrado;
        Vertice verticeEncontrado;
        verticeEncontrado = null;
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
            Nodo item = i.next();
            if (item.getVertice().getId().equals(id)){
                verticeEncontrado = item.getVertice();
            }

        }
        return verticeEncontrado;
    }
        
    public LinkedList<Nodo> getListaAdyacencia(){
        return listaDeAdyacencias;
    }

    public String toString() {
        String representacionGrafo;
        representacionGrafo = "";
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
                Nodo item = i.next();
                Vertice unVertice = item.getVertice();
                representacionGrafo += unVertice.toString();
                if (item.getRelacion()!=null){
                    item = item.getRelacion();
                    //representacionGrafo += "----" + item.getVertice().toString();
                    while (item!=null){
                    representacionGrafo += "----" + item.getVertice().toString();
                    item = item.getSiguiente();
                }
                }
                representacionGrafo += "\n";
        }
        return representacionGrafo;
    }
}
        /*
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
    */


    /*
    
    public int numeroDeVertices() {
        return nVertices;
    }

    
    public int numeroDeLados() {
        return nLados;
    }
    
    public boolean agregarVertice(Vertice v) {

        boolean seAgrego;
        seAgrego = false;

        if (listaDeAdyacencias.getExistenciaNodoVerticePorId(v.getId())==false){
            listaDeAdyacencias.agregarAlFinal(v);
            seAgrego = true;
        }
        return seAgrego;
        
    }


    public boolean agregarVertice(String id, double peso) {

        boolean seAgrego;
        Vertice verticeParaAgregar = new Vertice(id,peso);
        seAgrego = agregarVertice(verticeParaAgregar);
        return seAgrego;
    }

    
    

    /*
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

    */

    /*
    public boolean agregarArista(Arista a) {
    }

    public boolean agregarArista(String id, double peso, String u, String v) {
    }

    public boolean eliminarArista(String id) {
    }

    public Arista obtenerArista(String id) {
    }

    */

