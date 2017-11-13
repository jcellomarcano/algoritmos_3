import java.io.IOException;


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
        
        for (Vertice iterador : miGrafo.vertices()){
        	System.out.println(iterador.getIndice());
        }
	}
}