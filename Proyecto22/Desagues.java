import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class Desagues{

	public static void main(String[] args) throws IOException{
		
		GrafoDirigido miGrafo;
		miGrafo = new GrafoDirigido();
		miGrafo.cargarGrafo("prueba.txt");
		/*
		System.out.println(miGrafo.toString());
		System.out.println(miGrafo.vertices());
		System.out.println(miGrafo.lados());
        System.out.println("\n");
		*/
        Tarjan tar = new Tarjan(miGrafo);
		int mierda = tar.componenteConexa();
		System.out.printf(mierda + "componentes");

		LinkedList<Integer>[] componentes = (LinkedList<Integer>[]) new LinkedList[mierda];
		for (int i = 0; i<mierda; i++){
			componentes[i] = new LinkedList<Integer>();
		}
		for (int v = 0; v< miGrafo.numeroDeVertices(); v++){
			componentes[tar.]
		}
	}
}