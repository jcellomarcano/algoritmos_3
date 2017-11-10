/**
 * GrafoNoDirigido.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

/*Todas las librerias necesarias*/

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

public class GrafoNoDirigido implements Grafo{

    //Numero de Vertices del Grafo
    private int nVertices;
    //Numero de Lados del Grafo
    private int nLados;

    // Lista de Adyacencias que relacionara cada vertice con sus adyacentes
    private LinkedList<Nodo> listaDeAdyacencias = new LinkedList<Nodo>();

    /**
 * GrafoNoDirigido:
 * Crea un grafo no dirigido
 * Parametros de salida:
 * @param GrafoNoDirigido: objeto grafo dirigido
*/

    public GrafoNoDirigido() {
    }

    /**
 * cargarGrafo:
 * Carga en un grafo la informacion almacenada en el archivo de texto
 * Parametros de entrada:
 * @param g: grafo
 * @param archivo: string, archivo donde se encuentra el grafo
 * Parametros de salida:
 * @throws seCargo: boolean, los datos del archivo se cargaron exitosamente o caso contrario
*/

    public boolean cargarGrafo(String dirArchivo) throws IOException{

        /*Abrir y leer archivo txt, se va leyendo linea por linea*/

        BufferedReader in = new BufferedReader(new FileReader(dirArchivo));

        boolean seCargo=false; //Indica si se pudo cargar el Grafo o no, es lo que retorna la funcion

        /*Se leen las dos primeras lineas del archivo
        que corresponden al numero de Vertices y al numero de lados*/
        nVertices = Integer.parseInt(in.readLine());

        /*Se recorre las lineas del archivo correspondientes al numero de Vertices
        para crearlos */
        for (int i=0; i<nVertices; i++){
            String linea = in.readLine();
            String idDeNuevoVertice;
            int pesoNuevoVertice;
            int posX;
            int posY;
            Vertice nuevoVertice;
            Nodo nuevoNodo;

            /*Separamos en palabras para separar el id y el peso que se
            encuentran en la misma linea */
            StringTokenizer st = new StringTokenizer (linea);
            String palabra = st.nextToken();
            posX = Integer.parseInt(palabra);
            palabra = st.nextToken();
            posY = Integer.parseInt(palabra);

            /*Creamos los Vertices con el id y el peso y luego creamos
            los Nodos con estos vertices*/
            nuevoVertice = new Vertice(Integer.toString(i),0);
            nuevoVertice.setPos(posX,posY);
            nuevoNodo = new Nodo(nuevoVertice);

            /*Usamos la funcion add de LinkedList para agregar estos Nodos
            a nuestra lista de adyacencias*/
            listaDeAdyacencias.add(nuevoNodo); 

        }

        nLados = Integer.parseInt(in.readLine());
        /*Ahora recorremos tantas lineas como lados tiene para crear los lados y las relaciones*/
        for (int i=0; i<nLados; i++){
            String linea = in.readLine();
            String idDeNodoA;
            String idNodoB;
            int pesoNuevoLado;
            double distancia;
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


            idDeNodoA = palabra;
            palabra = st.nextToken();

            /*Obtenemos el id del primer Vertice a relacionar */
            idNodoB = palabra;
            nuevoNodoVerticeInicial = null;
            nuevoNodoVerticeFinal = null;

            /*Buscamos en cada Vertice el id que correspondan al primer vertice que se 
            va a relacionar */
            for (Iterator<Nodo> j = listaDeAdyacencias.iterator(); j.hasNext();) {
                Nodo item = j.next();
                if (item.getVertice().getId().equals(idDeNodoA)){
                    nuevoNodoVerticeInicial = item;
                    }
            }

             /*Obtenemos el id del segundo Vertice a relacionar */
            nuevoVerticeInicial = nuevoNodoVerticeInicial.getVertice();



            /*Buscamos en cada Vertice el id que correspondan al segundo vertice que se 
            va a relacionar */
            for (Iterator<Nodo> k = listaDeAdyacencias.iterator(); k.hasNext();) {
                Nodo item = k.next();
                if (item.getVertice().getId().equals(idNodoB)){
                    nuevoNodoVerticeFinal = item;
                    }
            }

            nuevoVerticeFinal = nuevoNodoVerticeFinal.getVertice();

            /*Creamos nuestra Arista*/
            distancia = Math.sqrt(Math.pow(nuevoVerticeInicial.getPosX() - nuevoVerticeFinal.getPosX(),2) + Math.pow(nuevoVerticeInicial.getPosY() - nuevoVerticeFinal.getPosY(),2));
            nuevaArista = new Arista(nuevoVerticeInicial.getId()+nuevoVerticeFinal.getId(),distancia,nuevoVerticeInicial,nuevoVerticeFinal);
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

/**
 * obtenerNodo:
 * Retorna el vertice contenido en el grafo que posee el identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws nodoDeVerticeEncontrado: objeto vertice
*/

    public Nodo obtenerNodo(String id){

        try{


        Nodo nodoDeVerticeEncontrado;
        Vertice verticeEncontrado;
        //nodoDeVerticeEncontrado = null;

        /*Iteramos en la lista de Adyacencia a ver si se encuentra el Nodo que buscamos*/
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
            Nodo item = i.next();
            if (item.getVertice().getId().equals(id)){
                nodoDeVerticeEncontrado = item;
                return nodoDeVerticeEncontrado;
            }

        }
        throw new NoSuchElementException();

        }

        catch(NoSuchElementException e){
            System.out.println("Error: Vertice "+id+" no existe");
            return null;
        }
        
    }

/**
 * obtenerVertice:
 * Retorna el vertice contenido en el grafo que posee el identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws verticeEncontrado: objeto vertice
*/

    public Vertice obtenerVertice(String id) throws RuntimeException{

        try{


        Nodo nodoDeVerticeEncontrado;
        Vertice verticeEncontrado;
        verticeEncontrado = null;
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();) {
            Nodo item = i.next();
            if (item.getVertice().getId().equals(id)){
                verticeEncontrado = item.getVertice();
                return verticeEncontrado;
            }

        }
        
        throw new NoSuchElementException();

        }

        catch(NoSuchElementException e){
            System.out.println("Se ha producido un error, no se encontro el vertice con id: " + id);
            return null;
        }
    }
        
    public LinkedList<Nodo> getListaAdyacencia(){
        return listaDeAdyacencias;
    }

/**
 * toString:
 * Deuelve una representacion del contenido de grafo como una cadena de caracteres
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws representacionGrafo: string, representacion del contenido del grafo
*/

    public String toString() {

        String representacionGrafo;
        List<Arista> listaDelado = new ArrayList<Arista>();
        representacionGrafo = "";

        /*Va recorriendo la lista de Adyacencias (los vertices)*/
        representacionGrafo += nVertices;
        representacionGrafo += "\n";
        representacionGrafo += nLados;
        representacionGrafo += "\n";
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator();i.hasNext();){
            Nodo item = i.next();
            Vertice unVertice = item.getVertice();
            representacionGrafo += unVertice.toString();
            representacionGrafo += "\n";
        }

        listaDelado = aristas();
        for (Iterator<Arista> i = listaDelado.iterator();i.hasNext();){
            Arista item = i.next();
            representacionGrafo += item.toString();
            representacionGrafo += "\n";

        }
        return representacionGrafo;
    }

    /**
 * numeroDeVertices:
 * Indica el numero de vertices que posee el grafo
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws nVertices: int, numero de vertices del grafo
*/

    public int numeroDeVertices() {
        return nVertices;
    }

    /**
 * numeroDeLados:
 * Indica el numero de lados que posee el grafo
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws numeroDeLados: int, numero de lados del grafo
*/
    public int numeroDeLados() {
        return nLados;
    }

/**
 * agregarVertice:
 * Agrega el vertice v al grafo g previamente creado
 * Parametros de entrada:
 * @param g: grafo
 * @param v: objeto vertice
 * Parametros de salida:
 * @throws seAgrego: boolean, el vertice se ha agregado exitosamente o caso contrario
*/

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

/**
 * agregarVertice:
 * Agrega el vertice v al grafo g previamente creado
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * @param peso: double, peso del vertice
 * Parametros de salida:
 * @throws seAgrego: boolean, el vertice se ha agregado exitosamente o caso contrario
*/

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

/**
 * estaVertice:
 * Se indica si un vertice con el identificador id se encuentra o no en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws estaElVertice: boolean, el vertice pertenece al grafo o caso contrario
*/

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

/**
 * estaLado:
 * Determina si un lado pertenece al grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param u: objeto vertice, vertice en el extremo inicial del lado
 * @param v: objeto vertice, vertice en el extremo final del lado
 * Parametros de salida:
 * @throws estaElLado: boolean, el lado se encuentra en el grafo o caso contrario
*/

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

/**
 * eliminarVertice:
 * Elimina el vertice del grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws estaElVertice: boolean, el vertice se ha eliminado exitosamente o caso contrario
*/

    public boolean eliminarVertice(String id) {

        /*Comprueba si el Vertice a eliminar se encuentra en el Grafo*/
        boolean estaElVertice = estaVertice(id);
        int contador = 0;
        int posicion = 0;

        if (estaElVertice==true){
            nVertices--;
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
                            nLados--;
                        }

                        else{
                            iterador.setRelacion(null);
                            nLados--;
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
                                    nLados--;
                                }

                                else{

                                    /*Caso en el que Vertice a eliminar este al final en los relacionados
                                     con el vertice que estamos revisando */
                                    iterador.setSiguiente(null);
                                    nLados--;
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

/**
 * vertices:
 * Retorna una lista con los vertices del grafo
 * Parametros de entrada:
 * @param g: grafo g
 * Parametros de salida:
 * @throws listaVertices: list, lista con los vertices del grafo g
*/

    public List<Vertice> vertices() {

        List<Vertice> listaDeVertice = new ArrayList<Vertice>();

        /*Recorre la lista adyacencia y va guardando los vertices por cada Nodo */
        for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                listaDeVertice.add(item.getVertice());
        }
        return listaDeVertice;
    }

/**
 * lados:
 * Retorna una lista con los lados del grafo
 * Parametros de entrada:
 * @param g: grafo g
 * Parametros de salida:
 * @throws listaLados: list, lista con los lados del grafo g
*/

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

    public List<Arista> aristas(){
        List<Arista> listaDeAristas = new ArrayList<Arista>();

            /*Recorre la lista de adyacencia y si el Nodo tiene relaciones, entonces
            va almacenando cada lado en la lista de lados */
            for (Iterator<Nodo> i = listaDeAdyacencias.iterator(); i.hasNext();){
                Nodo item = i.next();
                if(item.getRelacion()!=null){
                    Nodo iterador;
                    iterador = item.getRelacion();
                    while(iterador!=null){
                        boolean seRepite = false;
                        for (Iterator<Arista> j = listaDeAristas.iterator(); j.hasNext();){
                            Arista item2 = j.next();
                            if (item2.getId().equals(iterador.getLado().getId())){
                                seRepite = true;
                                break;
                            }
                        }

                        if (seRepite == false){
                            Arista arista1= new Arista(iterador.getLado().getId(),
                                                iterador.getLado().getPeso(),
                                                item.getVertice(),
                                                iterador.getVertice());
                            listaDeAristas.add(arista1); 
                        }
                        iterador = iterador.getSiguiente();

                    }
                    
                }
            }
            return listaDeAristas;
    }

/**
 * grado:
 * Calcula el grado del vertice identificado por id en el grafo g
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws entero, grado del vertice
*/


    public int grado(String id) throws RuntimeException{

        try{


        Nodo nodoABuscar;
        Nodo iterador;
        int contador;
        nodoABuscar = obtenerNodo(id);
        contador = 0;
        if (nodoABuscar!=null && nodoABuscar.getRelacion()!=null){
            iterador = nodoABuscar.getRelacion();
            /*Recorre los relacionados del vertice a revisar */
            while(iterador!=null){
                contador++;
                iterador = iterador.getSiguiente();
            }
        return contador;

        }
        throw new NoSuchElementException();
        }

        catch(NoSuchElementException e){
            System.out.println("Se ha producido un error, no se encontro el vertice con id: " + id);
            return 0;
        }
        
    }

/**
 * adyacentes:
 * Obtiene los vertices adyacentes al vertice identificado por id en el grafo g y los retorna en una lista
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws listaAdyacentes: list, lista con los vertices adyacentes a un vertice
*/

    public List<Vertice> adyacentes(String id) throws RuntimeException {

        try{

        
        List<Vertice> listaDeVertice = new ArrayList<Vertice>();
        Nodo nodoABuscar;
        nodoABuscar = obtenerNodo(id);
        if (nodoABuscar!= null && nodoABuscar.getRelacion()!=null){
            Nodo iterador;
            iterador = nodoABuscar.getRelacion();
            while (iterador!=null){
                listaDeVertice.add(iterador.getVertice());
                iterador = iterador.getSiguiente();
            }
            return listaDeVertice;
        }
        throw new NoSuchElementException();
        }

        catch(NoSuchElementException e){
            System.out.println("Se ha producido un error, no se encontro el vertice con id: " + id);
            return null;
        }
    }

/**
 * incidentes:
 * Obtiene los lados incidentes al vertice identificado por id en el grafo g y los retorna en una lista
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws listaIncidentes: list, lista con los lados incidentes a un vertice
*/

    public List<Lado> incidentes(String id) throws RuntimeException{

        try{
        List<Lado> listaDelado = new ArrayList<Lado>();
        Nodo nodoABuscar;
        nodoABuscar = obtenerNodo(id);
        if (nodoABuscar!=null && nodoABuscar.getRelacion()!=null){
            Nodo iterador;
            iterador = nodoABuscar.getRelacion();
            while (iterador!=null){
                listaDelado.add(iterador.getLado());
                iterador = iterador.getSiguiente();
            }
            return listaDelado;

        }
        throw new NoSuchElementException();
        }

        catch(NoSuchElementException e){
            System.out.println("Se ha producido un error, no se encontro el vertice con id: " + id);
            return null;
        }
        

    }

/**
 * agregarArista:
 * Agrega un nuevo arco al grafo g si el identificador del arco no lo posee ningun arco en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param a: arista, objeto arista
 * Parametros de salida:
 * @throws sePudoAgregar: boolean, el arco se ha agregado exitosamente o caso contrario
*/

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
                        nLados++;
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

/**
 * agregarArco:
 * Agrega un nuevo arco al grafo g si el identificador del arco no lo posee ningun arco en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, id de la arista 
 * @param peso: double, peso de la arista
 * @param u: string, id del vertice u
 * @param v: string, id del vertice v
 * Parametros de salida:
 * @throws sePudoAgregar: boolean, el arco se ha agregado exitosamente o caso contrario
*/

    public boolean agregarArista(String id, double peso, String u, String v) {

        boolean sePudoAgregar = true;
        Vertice verticeU;
        Vertice verticeV;
        verticeU = obtenerVertice(u);
        verticeV = obtenerVertice(v);
        Arista aristaAgregar = new Arista(id,peso,verticeU,verticeV);
        sePudoAgregar = agregarArista(aristaAgregar);
        if (sePudoAgregar == true){
            nLados++;
        }
        return sePudoAgregar;
    }

/**
 * eliminarArista:
 * Elimina el arco que este identificado con id en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del arista
 * Parametros de salida:
 * estaLaArista: boolean, el arco se ha eliminado exitosamente o caso contrario
*/

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

        if (estaLaArista == true){
            nLados--;
        }

        return estaLaArista;

        
    }

/**
 * obtenerArista:
 * Devuelve la arista que tiene como identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del arista
 * Parametros de salida:
 * @throws aristaABuscar, objeto arista
*/

    public Arista obtenerArista(String id) throws RuntimeException{


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
                        
                    }
                    iterador = iterador.getSiguiente();
                }

            }
        }

        return aristaABuscar;
   
    }

/**
 * clone:
 * Retorna un nuevo grafo con la misma composicion del grafo de entrada
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws clon: nuevo grafo
*/



    public GrafoNoDirigido clone(){


        GrafoNoDirigido clon = new GrafoNoDirigido();
        List<Vertice> listaDeVertice = new ArrayList<Vertice>();
        listaDeVertice = vertices();
        List<Arista> listaDeAristas = new ArrayList<Arista>();
        listaDeAristas = aristas();
        for (Iterator<Vertice> i = listaDeVertice.iterator(); i.hasNext();){
            Vertice item = i.next();
            clon.agregarVertice(item);
        }
         for (Iterator<Arista> j = listaDeAristas.iterator(); j.hasNext();){
            Arista item2 = j.next();
            clon.agregarArista(item2);
        }

        return clon;


    }


    
}   
