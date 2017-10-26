import java.util.*;
import java.io.*;

public class DeepWeb{

	public static void main(String[] args) {

		/*

		if (args.length() < 2 || args.length() > 8) {

			Se comprueba el numero de argumentos de pasados por consola,
			si son menores a 8 da un error 
      		System.out.println("Error en los argumentos del programa\nEjecutar:");
      		System.out.println("java DeepWeb <instancia> <origen> <dfs/bfs> [--trunc #] [--arb] [--ord] [--pred]");
      		System.exit(1);
    	}

    	if (args.length() = 2){
    		String instancia = args[0];
    		int origen = Integer.parseInt(args[1]);
    	}

    	*/




    	/*Recoje los datos pasados por consola en variables */
    	String instancia = args[0];
    	//int origen = Integer.parseInt(args[1]);
    	//String algoritmo = args[2];
    	//int profundidad = Integer.parseInt(args[4]);
    	//String arb = args[5];
    	//String ord = args[6];
    	//String pred = args[7];


    	/*Imprimimos los datos para comprobar que se leen correctamente*/


    	//System.out.println("la instancia es: " + instancia + " el origen es: " + origen
    						//+ " el algoritmo es: " + algoritmo + " la profundidad es: " + profundidad
    						//+ " el arbol es: " + arb + " el ord es: " + ord + " el pred es: " + pred);


    	/*Se crea objeto grafo dirigido*/

    	GrafoDirigido grafo;

    	grafo = new GrafoDirigido();

    	/*Se usa cargar grafo del del ejemplo 1 */
    	
    	grafo.cargarGrafo(instancia);

    	/*To string del grafo para imprimir en pantalla y saber que cargo correctamente*/

    	System.out.println(grafo.toString());

    	/*BFS desde el vertice 0 con el grafo*/

    	//bfs(grafo,0);


    	System.out.println();
    	System.out.println();
    	System.out.println();

    	BusquedaDFS midfs;

    	midfs = new BusquedaDFS (grafo);

    	System.out.println();
    	System.out.println();

    	//midfs.dfs(grafo);

    	midfs.printPath(1,4);

    	System.out.println();
	}
}
	/*

	public static void bfs(GrafoDirigido g,int vertice){
		int numeroV = g.numeroDeVertices();
		boolean visitados[] = new boolean[numeroV];
		LinkedList<Integer> cola = new LinkedList<Integer>();
		visitados[vertice]=true;
        cola.add(vertice);

        while (cola.size() != 0){
        	vertice = cola.poll();
            System.out.print(vertice+" ");
            List<Vertice> listaAdyacencia;
            listaAdyacencia = g.adyacentes(Integer.toString(vertice));
            Iterator<Vertice> i = listaAdyacencia.listIterator();
            while (i.hasNext()){
                Vertice n = i.next();
                int n1 = Integer.parseInt(n.getId());
                if (!visitados[n1]){
                    visitados[n1] = true;
                    cola.add(n1);
                }
            }

		}
	}
}

*/