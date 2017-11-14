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
    public void verificarCondiciones(){
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


        String[][] matriz;

        matriz = new String[grafo.getNumFilas()][grafo.getNumColumnas()];
        for (Vertice v : grafo.vertices()){
            String idDelVertice = v.getId();
            int posI = Integer.parseInt(idDelVertice.substring(0,1));
            int posJ = Integer.parseInt(idDelVertice.substring(1,2));
            if (v.getDesague()){
                matriz[posI][posJ] = "X";
            }
            else{
                matriz[posI][posJ] = "0";
            }
        }

        for (int i = 0; i<grafo.getNumFilas(); i++){
            for (int j=0; j<grafo.getNumColumnas();j++){
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }

        //System.out.println(listaSolucion);
    }
    public LinkedList<List<Vertice>> getSolucion(){return this.listaSolucion;}

}
