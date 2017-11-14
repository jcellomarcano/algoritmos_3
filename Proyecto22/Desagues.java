import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase main de Desagues
 * Jose Basanta 13-10125
 * Jesus Marcano 12-10359
 * */

public class Desagues{

	public static void main(String[] args) throws IOException{
		
		if (args.length!=1){
			System.out.println("Introdujo mal los argumentos");
			System.exit(1);
		}
		GrafoDirigido miGrafo;
		miGrafo = new GrafoDirigido();
		miGrafo.cargarGrafo(args[0]);
        
		//System.out.println(miGrafo.toString());
		//System.out.println(miGrafo.vertices());
		//System.out.println(miGrafo.numeroDeVertices());

		//System.out.println(miGrafo.lados());
        //System.out.println("\n");
     	

        List<List<Vertice>> componentes = new Tarjan1().cfc(miGrafo);
        System.out.println(componentes);
        Condiciones soluciones = new Condiciones(componentes,miGrafo);
        LinkedList<List<Vertice>> miListaSoluciones;
        soluciones.verificarCondiciones();
        soluciones.crearMatriz();


	}
}