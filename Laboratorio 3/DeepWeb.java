public class DeepWeb{

	public static void main(String[] args) {

		if (args.length != 8) {

			/*Se comprueba el numero de argumentos de pasados por consola,
			si son menores a 8 da un error */
      		System.out.println("Error en los argumentos del programa\nEjecutar:");
      		System.out.println("java LeerEscribirArchivo <nombreArchivoEntrada> <nombreArchivoSalida>");
      		//System.exit(1);
    	}

    	/*Recoje los datos pasados por consola en variables */
    	String instancia = args[0];
    	int origen = Integer.parseInt(args[1]);
    	String algoritmo = args[2];
    	int profundidad = Integer.parseInt(args[4]);
    	String arb = args[5];
    	String ord = args[6];
    	String pred = args[7];


    	/*Imprimimos los datos para comprobar que se leen correctamente*/
    	System.out.println("la instancia es: " + instancia + " el origen es: " + origen
    						+ " el algoritmo es: " + algoritmo + " la profundidad es: " + profundidad
    						+ " el arbol es: " + arb + " el ord es: " + ord + " el pred es: " + pred);


    	/*Se crea objeto grafo dirigido*/

    	GrafoDirigido grafo;

    	grafo = new GrafoDirigido();

    	/*Se usa cargar grafo del del ejemplo 1 */
    	
    	grafo.cargarGrafo("ejemplo1.txt");

    	/*To string del grafo para imprimir en pantalla y saber que cargo correctamente*/

    	System.out.println(grafo.toString());

	}
}