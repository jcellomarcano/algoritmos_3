import java.util.*;


public class DFSBacktracking{

	public DFSBacktracking(){

	}
	/*La funcion recibe el grafo a donde se va aplicar, 
	el vertice donde va a comenzar, en este caso la raiz, la pila, la suma de los pesos y la solucion con la que iremos comprobando */

	boolean busquedaDFSBacktracking(GrafoDirigido g, Vertice v, Vertice vAnterior,LinkedList<Vertice> cola,int suma,double solucion){


		/*Se le suma el peso del vertice actual a suma*/
        suma += v.getPeso();
        boolean esSolucion = false; //Variable que retornaremos, si es false no existe camino de dicha longitud

        if (suma>solucion){

			/*Si la suma excede la solucion, se intenta regresar a un vertice anterior a traves de la pila.
			Si no se puede, entonces deja de buscar */
            if (cola.size()!=0){
                Vertice verticeS;
                verticeS = cola.pollFirst();
                this.busquedaDFSBacktracking(g, verticeS, v, cola, verticeS.getSuma(),solucion);
            }

        }

		/*Obtenemos los sucesores del vertice que estamos analizando y se empilan*/
        List<Vertice> listaSucesores = g.sucesores(v.getId());
        if (listaSucesores.size()>0){
            int i=0;
            while(listaSucesores.size()!=i){
                listaSucesores.get(i).setSuma(suma);
                cola.addFirst(listaSucesores.get(i));
                i++;
            }

			/*Procede la recursion y ahora analizamos el vertice siguiente en la pila */
            if (cola.size()!=0){
                Vertice verticeS;
                verticeS = cola.pollFirst();
                this.busquedaDFSBacktracking(g, verticeS, v, cola, verticeS.getSuma(),solucion);
            }
        }

		else{

			/*Si al final (cuando ya no le queden mas sucesores al ultimo), la suma es igual a la solucion,
			entonces retorna true. Si no, regresa a un punto anterior (agarra de la pila)*/
            if (suma==solucion){
                esSolucion = true;

            }


			else{
                if (cola.size()!=0){
                    Vertice verticeS;
                    verticeS = cola.pollFirst();
                    this.busquedaDFSBacktracking(g, verticeS, v, cola, verticeS.getSuma(),solucion);
                }
            }

        }
        boolean encontradoSols = false;
        if (esSolucion!= true){
            return encontradoSols;
        }
        else{
            encontradoSols = true;
            return encontradoSols;
        }

	}
}