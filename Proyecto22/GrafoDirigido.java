/**
 * GrafoDirigido.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */


import java.util.*;
import java.io.*;

public class GrafoDirigido implements Grafo {

    private int numV;
    private int numL;
    private int numColumnas;
    private int numFilas;
    private LinkedList<LinkedList<Vertice>> g;
    private LinkedList<Arco> listaLados;

/**
 * GrafoDirigido:
 * Crea un grafo dirigido
 * Parametros de salida:
 * @param GrafoDirigido: objeto grafo dirigido
*/
    public GrafoDirigido() {
        numV = 0;
        numL = 0;
        numColumnas = 0;
        numFilas = 0;
        g = new LinkedList<LinkedList<Vertice>>();
        listaLados = new LinkedList<Arco>();
    }

/**
 * cargarGrafo:
 * Carga en un grafo la informacion almacenada en el archivo de texto
 * Parametros de entrada:
 * @param g: grafo
 * @param archivo: string, archivo donde se encuentra el grafo
 * Parametros de salida:
 * @throws grafoCargado: boolean, los datos del archivo se cargaron exitosamente o caso contrario
*/
    public boolean cargarGrafo(String dirArchivo) {
        try {
            Scanner in = new Scanner(new FileReader(dirArchivo));

            this.numFilas = Integer.parseInt(in.next());

            this.numColumnas = Integer.parseInt(in.next());

            boolean grafoCargado;
            double altura;
            int contador1;
            boolean confirmacion;
            contador1 = 0;

            for (int i=0; i<numFilas; i++) {
                for (int j=0; j<numColumnas;j++){
                    altura = Double.parseDouble(in.next());
                    String idVertice = Integer.toString(i)+"i"+Integer.toString(j)+"j";
                    
                    Vertice verticeAAgregar;
                    verticeAAgregar = new Vertice(idVertice, altura);
                    verticeAAgregar.setIndice((i*numColumnas) + j);
                    contador1++;
                    confirmacion = agregarVertice(verticeAAgregar);
                }
                
            }

            List<Vertice> listaVertices;
            listaVertices = this.vertices();
            String idVertice;
            String posX;
            String posY;
            int contador;
            contador = 0;
            for (Vertice iterador : listaVertices){
                idVertice = iterador.getId();
                int k = 0;
                posY = "";
                while (k!=idVertice.length()){
                    if (idVertice.substring(k,k+1).equals("i")){
                        posY = idVertice.substring(0,k);
                        break;
                    }
                    k++;
                    
                }
                
                posX = idVertice.substring(k+1,idVertice.length()-1);
                //System.out.println("PosX: " + posX);
                //System.out.println("PosY: " + posY);
                if (!posX.equals("0")){
                    Vertice verticeAComprobar;
                    String posX2;
                    posX2 = Integer.toString(Integer.parseInt(posX) - 1);
                    verticeAComprobar = g.get((Integer.parseInt(posY)*numColumnas)+Integer.parseInt(posX2)).get(0);
                    if (iterador.getPeso()>= verticeAComprobar.getPeso()){
                        Arco nuevoArco;
                        nuevoArco = new Arco(Integer.toString(contador),0, iterador, verticeAComprobar);
                        this.agregarArco(nuevoArco);
                        contador++;
                    }
                }

                else{
                    iterador.setFrontera(true);
                }

                if (!posX.equals(Integer.toString(numColumnas-1))){
                    Vertice verticeAComprobar;
                    String posX2;
                    posX2 = Integer.toString(Integer.parseInt(posX) + 1);
                    verticeAComprobar = g.get((Integer.parseInt(posY)*numColumnas)+Integer.parseInt(posX2)).get(0);
                    if (iterador.getPeso()>= verticeAComprobar.getPeso()){
                        Arco nuevoArco;
                        nuevoArco = new Arco(Integer.toString(contador),0, iterador, verticeAComprobar);
                        this.agregarArco(nuevoArco);
                        contador++;
                    }
                }

                else{
                    iterador.setFrontera(true);
                }

                if (!posY.equals("0")){
                    Vertice verticeAComprobar;
                    String posY2;
                    posY2 = Integer.toString(Integer.parseInt(posY) - 1);
                    verticeAComprobar = g.get((Integer.parseInt(posY2)*numColumnas)+Integer.parseInt(posX)).get(0);
                    if (iterador.getPeso()>= verticeAComprobar.getPeso()){
                        Arco nuevoArco;
                        nuevoArco = new Arco(Integer.toString(contador),0, iterador, verticeAComprobar);
                        this.agregarArco(nuevoArco);
                        contador++;
                    }
                }

                else{
                    iterador.setFrontera(true);
                }

                if (!posY.equals(Integer.toString(numFilas-1))){
                    Vertice verticeAComprobar;
                    String posY2;
                    posY2 = Integer.toString(Integer.parseInt(posY) + 1);
                    verticeAComprobar = g.get((Integer.parseInt(posY2)*numColumnas)+Integer.parseInt(posX)).get(0);
                    if (iterador.getPeso()>= verticeAComprobar.getPeso()){
                        Arco nuevoArco;
                        nuevoArco = new Arco(Integer.toString(contador),0, iterador, verticeAComprobar);
                        this.agregarArco(nuevoArco);
                        contador++;
                    }
                }

                else{
                    iterador.setFrontera(true);
                }

            }

            grafoCargado = true;
            return grafoCargado;
        }
        catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("No se pudo abrir el archivo: " + dirArchivo);
        }
    }

/**
 * numeroDeVertices:
 * Indica el numero de vertices que posee el grafo
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws numV: int, numero de vertices del grafo
*/
    public int numeroDeVertices() {
        return numV;
    }

/**
 * numeroDeLados:
 * Indica el numero de lados que posee el grafo
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws numL: int, numero de lados del grafo
*/
    public int numeroDeLados() {
        return numL;
    }

/**
 * agregarVertice:
 * Agrega el vertice v al grafo g previamente creado
 * Parametros de entrada:
 * @param g: grafo
 * @param v: objeto vertice
 * Parametros de salida:
 * @throws verticeAgregado: boolean, el vertice se ha agregado exitosamente o caso contrario
*/
    public boolean agregarVertice(Vertice v) {

        numV = numV + 1;
        LinkedList<Vertice> aux;
        aux = new LinkedList<Vertice>();
        aux.add(v);
        g.add(aux);
        return true;
    }

/**
 * agregarVertice:
 * Agrega el vertice v al grafo g previamente creado
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * @param peso: double, peso del vertice
 * Parametros de salida:
 * @throws verticeAgregado: boolean, el vertice se ha agregado exitosamente o caso contrario
*/
    public boolean agregarVertice(String id, double peso) {
        numV = numV + 1;
        Vertice v = new Vertice(id, peso);
        LinkedList<Vertice> aux;
        aux = new LinkedList<Vertice>();
        aux.add(v);
        g.add(aux);
        return true;
    }


/**
 * obtenerVertice:
 * Retorna el vertice contenido en el grafo que posee el identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws vertice: objeto vertice
*/
    public Vertice obtenerVertice(String id) {
        int posX;
        int posY;
        
        int k = 0;
        posY = 0;
        while (k!=id.length()){
            if (id.substring(k,k+1).equals("i")){
                posY = Integer.parseInt(id.substring(0,k));
                break;
            }
            k++;
                    
        }
        posX = Integer.parseInt(id.substring(k+1,id.length()-1));

        return g.get((posY*numColumnas)+posX).get(0);
    }

    public Vertice obtenerVerticePorInx(int indice) {
        return g.get(indice).get(0);
    }

/**
 * estaVertice:
 * Se indica si un vertice con el identificador id se encuentra o no en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws esta: boolean, el vertice pertenece al grafo o caso contrario
*/
    public boolean estaVertice(String id) {
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

/**
 * estaLado:
 * Determina si un lado pertenece al grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param u: objeto vertice, vertice en el extremo inicial del lado
 * @param v: objeto vertice, vertice en el extremo final del lado
 * Parametros de salida:
 * @throws esta: boolean, el lado se encuentra en el grafo o caso contrario
*/
    public boolean estaLado(String u, String v) {
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(u)){
                for (int j=1; j<g.get(i).size(); j++){
                    if (g.get(i).get(j).getId().equals(v)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

/**
 * eliminarVertice:
 * Elimina el vertice del grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws eliminado: boolean, el vertice se ha eliminado exitosamente o caso contrario
*/
    public boolean eliminarVertice(String id) {
        Vertice v;
        v = this.obtenerVertice(id);

        boolean eliminado;
        eliminado = false;

        for (int i=numV-1; i>-1; i--){
            if (g.get(i).get(0).getId().equals(id)){
                g.remove(i);
                eliminado = true;
                numV = numV - 1;
            }
            else if (g.get(i).contains(v)){
                g.get(i).remove(v);
            }
        }
        if (eliminado) {
            for (int i=numL-1; i>-1; i--){
                if (listaLados.get(i).getExtremoInicial().getId().equals(id) || listaLados.get(i).getExtremoFinal().getId().equals(id)){
                    listaLados.remove(i);
                    numL = numL - 1;
                }
            }
        }
        return eliminado;
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
        LinkedList<Vertice> listaVertices;
        listaVertices = new LinkedList<Vertice>();
        for (int i=0; i<numV; i++){
            listaVertices.add(g.get(i).get(0));
        }
        return listaVertices;
    }

/**
 * lados:
 * Retorna una lista con los lados del grafo
 * Parametros de entrada:
 * @param g: grafo g
 * Parametros de salida:
 * @throws listaLados: list, lista con los lados del grafo g
*/
    public List<Lado> lados() {
        LinkedList<Lado> listaLados2;
        listaLados2 = new LinkedList<Lado>();
        for (int i=0;i<numL;i++){
            listaLados2.add(listaLados.get(i));
        }
        return listaLados2;
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
    public int grado(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }
        return this.gradoInterior(id) + this.gradoExterior(id);
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
    public List<Vertice> adyacentes(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        LinkedList<Vertice> listaAdyacentes;
        listaAdyacentes = new LinkedList<Vertice>();
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(id)){
                for (int j=1; j<g.get(i).size(); j++){
                    listaAdyacentes.add(g.get(i).get(j));
                }
                return listaAdyacentes;
            }
        }
        return listaAdyacentes;
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
    public List<Lado> incidentes(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        LinkedList<Lado> listaIncidentes;
        listaIncidentes = new LinkedList<Lado>();
        for (int i=0; i<numL; i++){
            if (listaLados.get(i).getExtremoInicial().getId().equals(id) || listaLados.get(i).getExtremoFinal().getId().equals(id)){
                listaIncidentes.add(listaLados.get(i));
            }
            return listaIncidentes;
        }
        return listaIncidentes;
    }

/**
 * clone:
 * Retorna un nuevo grafo con la misma composicion del grafo de entrada
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws grafito: nuevo grafo
*/
    public GrafoDirigido clone() {
        GrafoDirigido grafito = new GrafoDirigido();
        for (int i=0; i<numV; i++) {
            grafito.agregarVertice(g.get(i).get(0));
        }
        for (int j=0; j<numL; j++) {
            grafito.agregarArco(listaLados.get(j));
        }
        return grafito;
    }

/**
 * toString:
 * Deuelve una representacion del contenido de grafo como una cadena de caracteres
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws aString: string, representacion del contenido del grafo
*/
    public String toString() {
    String aString = new String("");
    aString += numV + "\n" + numL;
    aString += "\n";
    for (int i=0; i<numV; i++) {
        aString = aString + g.get(i).get(0).toString();
        aString += "\n";
    }

    for (int j=0; j<numL; j++) {
        aString = aString + listaLados.get(j).toString();
        aString += "\n";
    }

    return aString;
    }

/**
 * agregarArco:
 * Agrega un nuevo arco al grafo g si el identificador del arco no lo posee ningun arco en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param a: objeto arco
 * Parametros de salida:
 * @throws arcoAgregado: boolean, el arco se ha agregado exitosamente o caso contrario
*/
    public boolean agregarArco(Arco a) {
        Vertice vi;
        Vertice vf;
        vi = a.getExtremoInicial();
        vf = a.getExtremoFinal();


        g.get(vi.getIndice()).add(vf);

        listaLados.add(a);
        numL = numL + 1;
        return true;

    }

/**
 * agregarArco:
 * Si el identificador del arco no lo posee ningun arco en el grafo, crea un nuevo arco y la agrega en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del arco
 * @param p: double, peso del arco
 * @param u: objeto vertice, vertice en el extremo inicial del arco
 * @param v: objeto vertice, vertice en el extremo final del arco
 * Parametros de salida:
 * @throws arcoAgregado: boolean, el arco se ha agregado exitosamente o caso contrario
*/
    public boolean agregarArco(String id, double peso, String vi, String vf) {
         if (this.estaVertice(vi) && this.estaVertice(vf)) {
            if (this.estaLado(vi, vf)){
                return false;
            }
            else {
                for (int i=0; i<numV; i++) {
                    if (g.get(i).get(0).getId().equals(vi)) {
                        g.get(i).add(this.obtenerVertice(vf));
                    }

                Arco a = new Arco(id, peso, this.obtenerVertice(vi), this.obtenerVertice(vf));
                listaLados.add(a);
                numL = numL + 1;
                return true;
                }
            }
        }
        return false;
    }

/**
 * eliminarArco:
 * Elimina el arco que este identificado con id en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del arco
 * Parametros de salida:
 * aristaEliminada: boolean, el arco se ha eliminado exitosamente o caso contrario
*/
    public boolean eliminarArco(String id) {
        for (int i=numL-1; i>-1; i--) {
            if (listaLados.get(i).getId().equals(id)) {

                Vertice vi;
                Vertice vf;
                vi = listaLados.get(i).getExtremoInicial();
                vf = listaLados.get(i).getExtremoFinal();
                for (int j=numV-1; j>-1; j--) {
                    if (g.get(i).get(0).getId().equals(vi.getId())) {
                        g.get(i).subList(1, g.get(i).size()).remove(vf);
                    }
                }
                listaLados.remove(i);
                numL = numL - 1;
                return true;
            }
        }
        return false;
    }

/**
 * obtenerArco:
 * Devuelve el arco que tiene como identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del arco
 * Parametros de salida:
 * @throws arco, objeto arco
*/
    public Arco obtenerArco(String id) {
        for (int i=0; i<numL; i++) {
            if (listaLados.get(i).getId().equals(id)) {
                return listaLados.get(i);
            }
        }
        throw new NoSuchElementException();
    }

/**
 * gradoInterior:
 * Calcula el grado interior del vertice identificado por id en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws gradoInterno: int, numero que representa el grado interno del vertice
*/
    public int gradoInterior(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        int gradoInterno;
        gradoInterno = 0;
        for (int i=0; i<numV; i++) {
            for (int j=1; j<g.get(i).size(); j++) {
                if (g.get(i).get(j).getId().equals(id)) {
                    gradoInterno = gradoInterno + 1;
                }
            } 
        }
        return gradoInterno;
    }

/**
 * gradoExterior:
 * Calcula el grado exterior del vertice identificado por id en el grafo
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws gradoExterno: int, numero que representa el grado externo del vertice
*/
    public int gradoExterior(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        int gradoExterno;
        gradoExterno = 0;
        for (int i=0; i<numV; i++) {
            if (g.get(i).get(0).getId().equals(id)) {
                gradoExterno = g.get(i).size()-1;
                return gradoExterno;
            }
        }
        return gradoExterno;
    }

/**
 * sucesores:
 * Devuelve una lista con los vertices sucesores del vertice con identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws list, lista de vertices sucesores del vertice dado
*/
    public List<Vertice> sucesores(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        LinkedList<Vertice> listaSucesores;
        listaSucesores = new LinkedList<Vertice>();

        for (Arco iterador : listaLados){
            if (iterador.getExtremoInicial().getId().equals(id)){
                listaSucesores.add(iterador.getExtremoFinal());
            }
        }
        return listaSucesores;

    }


    /**
 * predecesores:
 * Devuelve una lista con los vertices predecesores del vertice con identificador id
 * Parametros de entrada:
 * @param g: grafo
 * @param id: string, identificador del vertice
 * Parametros de salida:
 * @throws list, lista de vertices predecesores del vertice dado
*/
    public List<Vertice> predecesores(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        LinkedList<Vertice> listaPredecesores;
        listaPredecesores = new LinkedList<Vertice>();
        for (int i=0; i<numV; i++) {
            for (int j=1; j<g.get(i).size(); j++) {
                if (g.get(i).get(j).getId().equals(id)) {
                    listaPredecesores.add(g.get(i).get(0));
                }
            }
        }
        return listaPredecesores;
    }

    public int getNumColumnas(){
        return this.numColumnas;
    }

    public int getNumFilas(){
        return  this.numFilas;
    }


}
