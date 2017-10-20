public class DeepWeb{

	public static void main(String[] args) {

		if (args.length != 8) {
      		System.out.println("Error en los argumentos del programa\nEjecutar:");
      		System.out.println("java LeerEscribirArchivo <nombreArchivoEntrada> <nombreArchivoSalida>");
      		//System.exit(1);
    	}
    	String instancia = args[0];
    	int origen = Integer.parseInt(args[1]);
    	String algoritmo = args[2];
    	int profundidad = Integer.parseInt(args[4]);
    	String arb = args[5];
    	String ord = args[6];
    	String pred = args[7];

    	System.out.println("la instancia es: " + instancia + " el origen es: " + origen
    						+ " el algoritmo es: " + algoritmo + " la profundidad es: " + profundidad
    						+ " el arbol es: " + arb + " el ord es: " + ord + " el pred es: " + pred);

    	GrafoDirigido grafo;

    	grafo = new GrafoDirigido();
    	
    	grafo.cargarGrafo("ejemplo1.txt");

    	System.out.println(grafo.toString());

	}
}