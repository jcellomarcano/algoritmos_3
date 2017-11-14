import com.sun.javafx.collections.ListListenerHelper;

import java.util.List;

/**
 * Created by J&J on 13/11/2017.
 */
public class Condiciones {
    List<List<Vertice>> componentes;
    GrafoDirigido grafo;
    public Condiciones(List<List<Vertice>> componentes, GrafoDirigido grafo){
        this.componentes = componentes;
        this.grafo = grafo;
    }
    public void verificarCondiciones(){
         externo: for (List<Vertice> iterador : componentes) {
             int contador = 0;
             boolean[] arregloSolucionFuerte = new boolean[iterador.size()];
             boolean esSolucion = true;
            interno: for (Vertice iterador2: iterador) {
                if (!iterador2.getFrontera() ){

                    if(this.grafo.gradoExterior(iterador2.getId()) > 0  ){
                        List<Vertice> sucesores = grafo.sucesores(iterador2.getId());
                        for (Vertice w:sucesores ) {
                            if (w.getPeso() < iterador2.getPeso() ){

                                arregloSolucionFuerte[contador] = false;
                                break interno;
                            }
                            else {

                                arregloSolucionFuerte[contador] = true;

                            }
                        }
                    }

                    else{
                        arregloSolucionFuerte[contador] = true;
                    }   

                }

                else{
                    arregloSolucionFuerte[contador] = false;
                }

                contador++;
            }

            for (int i = 0; i<arregloSolucionFuerte.length; i++){
                if(arregloSolucionFuerte[i]!=true){
                    esSolucion = false;
                }
            }

            if (esSolucion){
                System.out.println("SoyLa componente" + iterador +"Soy solucions :D!!!!!");
            }
        }
    }
}
