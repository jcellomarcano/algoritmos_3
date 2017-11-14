import java.util.LinkedList;
import java.util.List;

/**
 * Created by J&J on 13/11/2017.
 */
public class Condiciones {
    List<List<Vertice>> componentes;
    GrafoDirigido grafo;
    LinkedList<List<Vertice>> listaSolucion;

    public Condiciones(List<List<Vertice>> componentes, GrafoDirigido grafo){
        this.componentes = componentes;
        this.grafo = grafo;
        this.listaSolucion = new LinkedList<List<Vertice>>();
    }
    public LinkedList<List<Vertice>> verificarCondiciones(){
         externo: for (List<Vertice> iterador : componentes) {
             int contador = 0;
             boolean[] arregloSolucionFuerte = new boolean[iterador.size()];
             boolean esSolucion = true;
             interno:
             for (Vertice iterador2 : iterador) {
                 if (!iterador2.getFrontera()) {

                     if (this.grafo.gradoExterior(iterador2.getId()) > 0) {
                         List<Vertice> sucesores = grafo.sucesores(iterador2.getId());
                         for (Vertice w : sucesores) {
                             if (w.getPeso() < iterador2.getPeso()) {

                                 arregloSolucionFuerte[contador] = false;
                                 break interno;
                             } else {

                                 arregloSolucionFuerte[contador] = true;

                             }
                         }
                     } else {
                         arregloSolucionFuerte[contador] = true;
                     }

                 } else {
                     arregloSolucionFuerte[contador] = false;
                 }

                 contador++;
             }

             for (boolean anArregloSolucionFuerte : arregloSolucionFuerte) {
                 if (!anArregloSolucionFuerte) {
                     esSolucion = false;
                 }
             }

             if (esSolucion) { 
                 this.listaSolucion.add(iterador);
             }
         }

          for (List<Vertice> componente : listaSolucion) {
             for (Vertice v : componente) {
                 v.setDesague(true);
             }
          }

        return listaSolucion;

    }

    public void crearMatriz(){


        String[][] matriz;

        matriz = new String[grafo.getNumFilas()][grafo.getNumColumnas()];
        for (Vertice v : grafo.vertices()){
            String idDelVertice = v.getId();

            int posI=999;
            int posJ=999;

            int k=0;
            while (k!=idDelVertice.length()){
                if (idDelVertice.substring(k,k+1).equals("i")){
                    posI = Integer.parseInt(idDelVertice.substring(0,k));
                    break;
                }
                k++;
                    
            }
                
            posJ = Integer.parseInt(idDelVertice.substring(k+1,idDelVertice.length()-1));

            if (v.getDesague()){
                matriz[posI][posJ] = "x";
            }
            else{
                matriz[posI][posJ] = "0";
            }
        }

        for (int i = 0; i<grafo.getNumFilas(); i++){
            for (int j=0; j<grafo.getNumColumnas();j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(listaSolucion);
    }


    public LinkedList<List<Vertice>> getSolucion(){return this.listaSolucion;}

}
