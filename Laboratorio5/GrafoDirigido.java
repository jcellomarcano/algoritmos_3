/**
 * GrafoDirigido.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */


import java.util.*;
import java.io.*;

public class GrafoDirigido {

    private int numV;
    private int numL;
    private LinkedList<LinkedList<Vertice>> g;
    private LinkedList<Arco> listaLados;



/**
 * GrafoDirigido:
 * Crea un grafo dirigido
 * Parametros de salida:
 * @param GrafoDirigido: objeto grafo dirigido
*/
    @SuppressWarnings("JavadocReference")
    public GrafoDirigido() {
        int numV = 0;
        int numL = 0;
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
 * @throws grafoCargado: boolean, los da tos del archivo se cargaron exitosamente o caso contrario
*/
    @SuppressWarnings("JavadocReference")
    public boolean cargarGrafo(String expresionArbol) {
       boolean grafoCargado = false;
       for (int i=0;i<expresionArbol.length();i++){
        int contadorHijos=0;
        if(! ( expresionArbol.substring(i,i+1).equals("(") ) && !(expresionArbol.substring(i,i+1).equals(")") )){
            int j = i;
            while(! ( expresionArbol.substring(j,j+1).equals("(") ) && !(expresionArbol.substring(j,j+1).equals(")") )){
                j=j+1;
            }
            Vertice v;
            v = new Vertice(Integer.toString(i), Double.parseDouble(expresionArbol.substring(i,j)));
            this.agregarVertice(v);
            int contadorParentesis = 0;
            i = j;
            for (int k=i;k<expresionArbol.length();k++){
                //System.out.println(contadorParentesis);

                if( expresionArbol.substring(k,k+1).equals("(") ){
                    contadorParentesis++;
                }

                else if( expresionArbol.substring(k,k+1).equals(")") ){
                    contadorParentesis--;
                }

                

                else if(! ( expresionArbol.substring(k,k+1).equals("(") ) && !(expresionArbol.substring(k,k+1).equals(")") ) && contadorParentesis==1){
                    int l = k;
                    boolean lAumento = false;
                    while(! ( expresionArbol.substring(l,l+1).equals("(") ) && !(expresionArbol.substring(l,l+1).equals(")") )){
                        l=l+1;
                        lAumento = true;
                    }
                    Vertice v1;
                    v1 = new Vertice(Integer.toString(k), Double.parseDouble(expresionArbol.substring(k,l)));
                    this.agregarVertice(v1);
                    Arco a;
                    a = new Arco(Integer.toString(numL),(double)numL,v,v1);
                    this.agregarArco(a);
                    if (lAumento){
                        k=l-1;
                    }
                    else{
                        k=l;
                    }
                }

                if(contadorParentesis==0){
                    contadorHijos++;
                }

                if(contadorHijos==2){
                    break;
                }


                


            }
            
        }
       }
       return grafoCargado;
       
            
    }

/**
 * numeroDeVertices:
 * Indica el numero de vertices que posee el grafo
 * Parametros de entrada:
 * @param g: grafo
 * Parametros de salida:
 * @throws numV: int, numero de vertices del grafo
*/
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
    public boolean agregarVertice(Vertice v) {
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(v.getId())){
                return false;
            }
        }
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
    @SuppressWarnings("JavadocReference")
    public boolean agregarVertice(String id, double peso) {
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(id)){
                return false;
            }
        }
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
    @SuppressWarnings("JavadocReference")
    public Vertice obtenerVertice(String id) {
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(id)){
                return g.get(i).get(0);
            }
        }
        throw new NoSuchElementException();
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
    public List<Integer> adyacentes(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();
        }

        LinkedList<Integer> listaAdyacentes;
        listaAdyacentes = new LinkedList<Integer>();
        for (int i=0; i<numV; i++){
            if (g.get(i).get(0).getId().equals(id)){
                for (int j=1; j<g.get(i).size(); j++){
                    listaAdyacentes.add(Integer.parseInt(g.get(i).get(j).getId()));
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
    public boolean agregarArco(Arco a) {
        Vertice vi;
        Vertice vf;
        vi = a.getExtremoInicial();
        vf = a.getExtremoFinal();

        if (this.estaVertice(vi.getId()) && this.estaVertice(vf.getId())) {
            if (this.estaLado(vi.getId(), vf.getId())){
                return false;
            }
            else {
                for (int i=0; i<numV; i++) {
                    if (g.get(i).get(0).getId().equals(vi.getId())) {
                        g.get(i).add(vf);
                    }
                                }
                listaLados.add(a);
                numL = numL + 1;
                return true;
            }
        }
        return false;
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
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
    @SuppressWarnings("JavadocReference")
    public List<Vertice> sucesores(String id) {
        if (!this.estaVertice(id)) {
            throw new NoSuchElementException();

        }

        for (int i=0; i<numV; i++) {
            if (g.get(i).get(0).getId().equals(id)) {
                return g.get(i).subList(1, g.get(i).size());
            }
        }
        return g.get(0);

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
    @SuppressWarnings("JavadocReference")
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


}
