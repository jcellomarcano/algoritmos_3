/**
 * 
 */

/*Todas las librerias necesarias*/

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

    //Numero de Vertices del Grafo
    private int nVertices;
    //Numero de Lados del Grafo
    private int nLados;

    // Lista de Adyacencias que relacionara cada vertice con sus adyacentes
    private LinkedList<Nodo> listaDeAdyacencias = new LinkedList<Nodo>();

    public GrafoNoDirigido() {

    }

    public boolean cargarGrafo(String dirArchivo) throws IOException{

        /*Abrir y leer archivo txt, se va leyendo linea por linea*/

        BufferedReader in = new BufferedReader(new FileReader(dirArchivo));

        boolean seCargo=false; //Indica si se pudo cargar el Grafo o no, es lo que retorna la funcion

        /*Se leen las dos primeras lineas del archivo
        que corresponden al numero de Vertices y al numero de lados*/
        nVertices = Integer.parseInt(in.readLine());
        nLados = Integer.parseInt(in.readLine());

        /*Se recorre las lineas del archivo correspondientes al numero de Vertices
        para crearlos */
        for (int i=0; i<nVertices; i++){
            String linea = in.readLine();
            String idDeNuevoVertice;
            int pesoNuevoVertice;
            Vertice nuevoVertice;
            Nodo nuevoNodo;

            /*Separamos en palabras para separar el id y el peso que se
            encuentran en la misma linea */
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            idDeNuevoVertice = palabra;
            palabra = st.nextToken();
            pesoNuevoVertice = Integer.parseInt(palabra);

            /*Creamos los Vertices con el id y el peso y luego creamos
            los Nodos con estos vertices*/
            nuevoVertice = new Vertice(idDeNuevoVertice,pesoNuevoVertice);
            nuevoNodo = new Nodo(nuevoVertice);

            /*Usamos la funcion add de LinkedList para agregar estos Nodos
            a nuestra lista de adyacencias*/
            listaDeAdyacencias.add(nuevoNodo); 

        }
        /*Ahora recorremos tantas lineas como lados tiene para crear los lados y las relaciones*/
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

            /*Obtenemos el id del nuevo Lado, separando por palabras */
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            idDeNuevoLado = palabra;
            palabra = st.nextToken();

            /*Obtenemos el id del primer Vertice a relacionar */
            idVerticeARelacionar = palabra;
            nuevoNodoVerticeInicial = null;
            nuevoNodoVerticeFinal = null;

            /*Buscamos en cada Vertice el id que correspondan al primer vertice que se 
            va a relacionar */
            for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();) {
                Nodo item = j.next();
                if (item.getVertice().getId().equals(idVerticeARelacionar)){
                    nuevoNodoVerticeInicial = item;
                    }
            }

             /*Obtenemos el id del segundo Vertice a relacionar */
            nuevoVerticeInicial = nuevoNodoVerticeInicial.getVertice();
            palabra = st.nextToken();
            idVerticeARelacionar = palabra;


            /*Buscamos en cada Vertice el id que correspondan al segundo vertice que se 
            va a relacionar */
            for (Iterator<Nodo> k = listaDeAdyacencias.iterator(); k.hasNext();) {
                Nodo item = k.next();
                if (item.getVertice().getId().equals(idVerticeARelacionar)){
                    nuevoNodoVerticeFinal = item;
                    }
            }

            nuevoVerticeFinal = nuevoNodoVerticeFinal.getVertice();

            /*Obtenemos el peso del nuevo Lado*/
            palabra = st.nextToken();
            pesoNuevoLado = Integer.parseInt(palabra);

            /*Creamos nuestra Arista*/
            nuevaArista = new Arista(idDeNuevoLado,pesoNuevoLado,nuevoVerticeInicial,nuevoVerticeFinal);
            nuevoNodo = new Nodo (nuevoVerticeFinal,nuevaArista);
            nuevoNodo2 = new Nodo (nuevoVerticeInicial,nuevaArista); //Para agregar tambien la relacion al otro lado

                /*Se recorre en la lista de adyacentes del primer Vertice y se agrega el 
                segundo vertice al final de esta lista*/
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

                /*Se agrega tambien la relacion hacia el sentido contrario porque es no dirigido */
                /*Se recorre en la lista de adyacentes del segundo Vertice y se agrega el 
                primer vertice al final de esta lista*/

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
        /*Retorna si se pudo cargar el grafo*/
        return seCargo;
    }

    public Nodo obtenerNodo(String id){
        Nodo nodoDeVerticeEncontrado;
        Vertice verticeEncontrado;
        nodoDeVerticeEncontrado = null;

        /*Iteramos en la lista de Adyacencia a ver si se encuentra el Nodo que buscamos*/
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
            Nodo item = i.next();
            if (item.getVertice().getId().equals(id)){
                nodoDeVerticeEncontrado = item;
            }

        }
        return nodoDeVerticeEncontrado;
    }

    public Vertice obtenerVertice(String id) {

        /*Iteramos en la lista de Adyacencias comparando el id de cada 
        Vertice asociado a cada Nodo comprobando si existe*/
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

        /*Va recorriendo la lista de Adyacencias (los vertices)*/
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
                Nodo item = i.next();
                Vertice unVertice = item.getVertice();
                representacionGrafo += unVertice.toString();
                if (item.getRelacion()!=null){
                    /*Va recorriendo los adyacentes de cada vertice */
                    item = item.getRelacion();
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

        /*Comprueba si el vertice agregado ya existe */
        for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();){
            Nodo item = j.next();
            if (item.getVertice().getId().equals(v.getId())){
                sePuedeAgregar = false;
                break;
            }
        }

        if (sePuedeAgregar == true){
            //Si no existe, se agrega
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

        /*Comprueba si el vertice agregado ya existe */
        for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();){
            Nodo item = j.next();
            if (item.getVertice().getId().equals(id)){
                sePuedeAgregar = false;
                break;
            }
        }

        if (sePuedeAgregar == true){
            //Si no existe, se agrega
            listaDeAdyacencias.add(nuevoNodo);
            nVertices++;
            seAgrego = true;
        }
        return seAgrego;
    }

    public boolean estaVertice(String id) {
        boolean estaElVertice = false;

        /*Itera y busca en la lista de adyacencia si esta el vertice */
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

        /*Itera y busca en las relaciones de cada Vertice si es el lado*/
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

        /*Comprueba si el Vertice a eliminar se encuentra en el Grafo*/
        boolean estaElVertice = estaVertice(id);
        int contador = 0;
        int posicion = 0;

        if (estaElVertice==true){
            for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();

                /*Guarda en que posicion de lista de adyacencia se encuentra el vertice */
                if (item.getVertice().getId().equals(id)){
                    posicion = contador;
                }

                /*Pasa a revisar en cada Vertice si esta relacionado con el vertice a 
                eliminar */

                if (item.getRelacion()!=null){
                    Nodo iterador = item;
                    Nodo iterador2 = item.getRelacion();

                    /*Caso en que el Vertice a eliminar sea el primero en los relacionados 
                    con el vertice que estamos revisando */
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
                            /*Caso en que el Vertice a eliminar este en el medio en los relacionados
                            con el vertice que estamos revisando */
                            if (iterador2.getVertice().getId().equals(id)){

                                if (iterador2.getSiguiente()!=null){
                                    iterador.setSiguiente(iterador2.getSiguiente());
                                }

                                else{

                                    /*Caso en el que Vertice a eliminar este al final en los relacionados
                                     con el vertice que estamos revisando */
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

        /*Recorre la lista adyacencia y va guardando los vertices por cada Nodo */
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                listaDeVertice.add(item.getVertice());
        }
        return listaDeVertice;
    }

    public List<Lado> lados(){
        List<Lado> listaDelado = new ArrayList<Lado>();

            /*Recorre la lista de adyacencia y si el Nodo tiene relaciones, entonces
            va almacenando cada lado en la lista de lados */
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
            /*Recorre los relacionados del vertice a revisar */
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
        Nodo nodoU;
        Nodo nodoV;
        Nodo nodoNuevoU;
        Nodo nodoNuevoV;

        seAgrego = true;
        u = a.getExtremo1();
        v = a.getExtremo2();



        /*Recorre en todos los Vertices si existe la Arista que queremos agregar, es 
        decir mismo id */

        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                if (u.getId().equals(item.getVertice().getId())){
                    Nodo iterador;
                    if (item.getRelacion()!=null){
                        iterador = item.getRelacion();
                        while(iterador!=null){
                            if(iterador.getLado().getId().equals(a.getId())){
                                seAgrego = false;
                                break;
                            }
                            iterador = iterador.getSiguiente();
                        }  
                    }

                    if (seAgrego==true){ //Si no existe, la agregamos
                        nodoU = obtenerNodo(u.getId());
                        nodoNuevoU = new Nodo(u,a);
                        nodoV = obtenerNodo(v.getId());
                        nodoNuevoV = new Nodo(v,a);

                        if (nodoU.getRelacion()==null){
                            nodoU.setRelacion(nodoNuevoV);
                        }
                        else{
                            Nodo iterador2;
                            iterador2 = nodoU.getRelacion();
                            while (iterador2.getSiguiente()!=null){
                                iterador2 = iterador2.getSiguiente();
                            } 
                            iterador2.setSiguiente(nodoNuevoV);
                        }

                /*Se agrega tambien la relacion hacia el sentido contrario */

                        if (nodoV.getRelacion()==null){
                            nodoV.setRelacion(nodoNuevoU);
                        }
                        else{
                            Nodo iterador2;
                            iterador2 = nodoV.getRelacion();
                            while (iterador2.getSiguiente()!=null){
                                iterador2 = iterador2.getSiguiente();
                            } 
                            iterador2.setSiguiente(nodoNuevoU);
                        }

                    }
                    
                }

        }

        return seAgrego;


    }

    public boolean agregarArista(String id, double peso, String u, String v) {

        boolean sePudoAgregar = true;
        Vertice verticeU;
        Vertice verticeV;
        verticeU = obtenerVertice(u);
        verticeV = obtenerVertice(v);
        Arista aristaAgregar = new Arista(id,peso,verticeU,verticeV);
        sePudoAgregar = agregarArista(aristaAgregar);
        return sePudoAgregar;
    }

    public boolean eliminarArista(String id) {
        boolean estaLaArista;
        estaLaArista = false;

        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
            Nodo item = i.next();


            /*Pasa a revisar en cada Vertice si esta relacionado con la Arista a 
            eliminar */

            if (item.getRelacion()!=null){
                Nodo iterador = item;
                Nodo iterador2 = item.getRelacion();

                /*Caso en que la Arista a eliminar sea el primero en los relacionados 
                con el vertice que estamos revisando */
                if (iterador2.getLado().getId().equals(id)){
                        
                    if (iterador2.getSiguiente()!=null){
                        iterador.setRelacion(iterador2.getSiguiente());
                        estaLaArista = true;
                        break;
                    }

                    else{
                        iterador.setRelacion(null);
                        estaLaArista = true;
                        break;
                    }
                }

                else{

                    iterador = item.getRelacion();
                    iterador2 = iterador2.getSiguiente();
                    while (iterador2!=null){
                        /*Caso en que la Arista a eliminar este en el medio en los relacionados
                        con el vertice que estamos revisando */
                        if (iterador2.getLado().getId().equals(id)){

                            if (iterador2.getSiguiente()!=null){
                                iterador.setSiguiente(iterador2.getSiguiente());
                                estaLaArista = true;
                                break;
                            }

                            else{

                                /*Caso en el que La Arista a eliminar este al final en los relacionados
                                     con el vertice que estamos revisando */
                                iterador.setSiguiente(null);
                                estaLaArista = true;
                                break;
                            }
                        }
                        iterador = iterador.getSiguiente();
                        iterador2 = iterador2.getSiguiente();
                    }
                  
                }

                    
            }

        }

        return estaLaArista;

        
    }

    public Arista obtenerArista(String id) {

        Arista aristaABuscar;
        aristaABuscar = null;

        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
            Nodo item = i.next();
            if(item.getRelacion()!=null){
                Nodo iterador;
                iterador = item.getRelacion();
                while(iterador!=null){
                    if (iterador.getLado().getId().equals(id)){

                        aristaABuscar = new Arista(iterador.getLado().getId(),
                                                iterador.getLado().getPeso(),
                                                item.getVertice(),
                                                iterador.getVertice());
                        break;
                    }
                    iterador = iterador.getSiguiente();
                }
            }
        }

        return aristaABuscar;
    }

        


    //public Object clone() {
    //

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
    



    

    

    */

