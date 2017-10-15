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
            Nodo nuevoNodo2;
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
            nuevoNodo2 = new Nodo (nuevoVerticeInicial,nuevaArista); //Para agregar tambien la relacion al otro lado

                if (nuevoNodoVerticeInicial.getRelacion()==null){
                    nuevoNodoVerticeInicial.setRelacion(nuevoNodo);
                }
                else{
                    Nodo iterador;
                    iterador = nuevoNodoVerticeInicial.getRelacion();
                    while (iterador.getSiguiente()!=null){
                        iterador = iterador.getSiguiente();
                    } 
                    iterador.setSiguiente(nuevoNodo);
                }

                /*Se agrega tambien la relacion hacia el sentido contrario */

                if (nuevoNodoVerticeFinal.getRelacion()==null){
                    nuevoNodoVerticeFinal.setRelacion(nuevoNodo2);
                }
                else{
                    Nodo iterador;
                    iterador = nuevoNodoVerticeFinal.getRelacion();
                    while (iterador.getSiguiente()!=null){
                        iterador = iterador.getSiguiente();
                    } 
                    iterador.setSiguiente(nuevoNodo2);
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

    public int numeroDeVertices() {
        return nVertices;
    }

    
    public int numeroDeLados() {
        return nLados;
    }

    public boolean agregarVertice(Vertice v) {
        Nodo nuevoNodo = new Nodo(v);
        boolean sePuedeAgregar = true;
        boolean seAgrego = false;
        for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();){
            Nodo item = j.next();
            if (item.getVertice().getId().equals(v.getId())){
                sePuedeAgregar = false;
                break;
            }
        }

        if (sePuedeAgregar == true){
            listaDeAdyacencias.add(nuevoNodo);
            nVertices++;
            seAgrego = true;
        }
        return seAgrego;
    }

    public boolean agregarVertice(String id, double peso) {
        Vertice nuevoVertice = new Vertice(id, peso);
        Nodo nuevoNodo = new Nodo(nuevoVertice);
        boolean sePuedeAgregar = true;
        boolean seAgrego = false;
        for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();){
            Nodo item = j.next();
            if (item.getVertice().getId().equals(id)){
                sePuedeAgregar = false;
                break;
            }
        }

        if (sePuedeAgregar == true){
            listaDeAdyacencias.add(nuevoNodo);
            nVertices++;
            seAgrego = true;
        }
        return seAgrego;
    }

    public boolean estaVertice(String id) {
        boolean estaElVertice = false;
        for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();){
            Nodo item = j.next();
            if (item.getVertice().getId().equals(id)){
                estaElVertice = true;
                break;
            }
        }
        return estaElVertice;
    }

    public boolean estaLado(String u, String v){
        boolean estaElLado = false;
        boolean estaU = false;
        boolean estaV = false;
        for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();){
            Nodo item = j.next();
            if (item.getVertice().getId().equals(u)){
                estaU = true;
                Nodo iterador;
                iterador = item.getRelacion();
                while(iterador!=null){
                    if (iterador.getVertice().getId().equals(v)){
                        estaV = true;
                        estaElLado = true;
                    }
                    iterador = iterador.getSiguiente();
                }
            }
        }
        return estaElLado;

    }

    public boolean eliminarVertice(String id) {
        boolean estaElVertice = estaVertice(id);
        int contador = 0;
        int posicion = 0;

        if (estaElVertice==true){
            for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();

                if (item.getVertice().getId().equals(id)){
                    posicion = contador;
                }

                if (item.getRelacion()!=null){
                    Nodo iterador = item;
                    Nodo iterador2 = item.getRelacion();

                    if (iterador2.getVertice().getId().equals(id)){
                        
                        if (iterador2.getSiguiente()!=null){
                            iterador.setRelacion(iterador2.getSiguiente());
                        }

                        else{
                            iterador.setRelacion(null);
                        }
                    }

                    else{
                        iterador = item.getRelacion();
                        iterador2 = iterador2.getSiguiente();
                        while (iterador2!=null){
                            if (iterador2.getVertice().getId().equals(id)){

                                if (iterador2.getSiguiente()!=null){
                                    iterador.setSiguiente(iterador2.getSiguiente());
                                }

                                else{
                                    iterador.setSiguiente(null);
                                }
                            }
                        iterador = iterador.getSiguiente();
                        iterador2 = iterador2.getSiguiente();
                    }
                  
                    }

                    
                }

                contador++;

            }

        if (estaElVertice==true){
            listaDeAdyacencias.remove(posicion);
        }
        } 
        
        return estaElVertice;       
    }

    public List<Vertice> vertices() {

        List<Vertice> listaDeVertice = new ArrayList<Vertice>();
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                listaDeVertice.add(item.getVertice());
        }
        return listaDeVertice;
    }

    public List<Lado> lados(){
        List<Lado> listaDelado = new ArrayList<Lado>();
            for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                if(item.getRelacion()!=null){
                    Nodo iterador;
                    iterador = item.getRelacion();
                    while(iterador!=null){
                      listaDelado.add(iterador.getLado());
                      iterador = iterador.getSiguiente(); 
                    }
                    
                }
            }
            return listaDelado;
    }

    public int grado(String id){
        Nodo nodoABuscar;
        Nodo iterador;
        int contador;
        nodoABuscar = obtenerNodo(id);
        contador = 0;
        if (nodoABuscar.getRelacion()!=null){
            iterador = nodoABuscar.getRelacion();
            while(iterador!=null){
                contador++;
                iterador = iterador.getSiguiente();
            }

        }
        return contador;
    }

    public List<Vertice> adyacentes(String id) {
        List<Vertice> listaDeVertice = new ArrayList<Vertice>();
        Nodo nodoABuscar;
        nodoABuscar = obtenerNodo(id);
        if (nodoABuscar.getRelacion()!=null){
            Nodo iterador;
            iterador = nodoABuscar.getRelacion();
            while (iterador!=null){
                listaDeVertice.add(iterador.getVertice());
                iterador = iterador.getSiguiente();
            }

        }


        return listaDeVertice;
    }

    public List<Lado> incidentes(String id){
        List<Lado> listaDelado = new ArrayList<Lado>();
        Nodo nodoABuscar;
        nodoABuscar = obtenerNodo(id);
        if (nodoABuscar.getRelacion()!=null){
            Nodo iterador;
            iterador = nodoABuscar.getRelacion();
            while (iterador!=null){
                listaDelado.add(iterador.getLado());
                iterador = iterador.getSiguiente();
            }

        }
        return listaDelado;

    }

    public boolean agregarArista(Arista a) {

        boolean seAgrego;
        Vertice u;
        Vertice v;
        Vertice encontrado;

        seAgrego = true;
        u = a.getExtremo1();
        v = a.getExtremo2();

        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                if (u.getVertice().getId().equals(item.getVertice().getId())){
                    Nodo iterador;
                    if (item.getRelacion()!=null){
                        iterador = item.getRelacion();
                        while(iterador!=null){
                            if(iterador.getLado().getId().equals(a.getId())){
                                seAgrego = false;
                            }
                        }  
                    }
                    
                }

        }


    }

    //public Object clone() {
    //}



}   
    

    /*
    
    


    

    
    

    /*
    

    

    public List<Lado> lados() {
    }

    public int grado(String id) {
    }

    
 
    public List<Lado> incidentes(String id) {
    }

    public Object clone() {
    }

    */

    /*
    

    public boolean agregarArista(String id, double peso, String u, String v) {
    }

    public boolean eliminarArista(String id) {
    }

    public Arista obtenerArista(String id) {
    }

    */

