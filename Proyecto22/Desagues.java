import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Desagues{

	public static void main(String[] args) throws IOException{
		
		GrafoDirigido miGrafo;
		miGrafo = new GrafoDirigido();
		miGrafo.cargarGrafo("prueba.txt");

		System.out.println(miGrafo.toString());
		System.out.println(miGrafo.vertices());
		System.out.println(miGrafo.lados());
        System.out.println("\n");

        List<List<Vertice>> componentes = new Tarjan1().cfc(miGrafo);
        System.out.println(componentes);

	}
}